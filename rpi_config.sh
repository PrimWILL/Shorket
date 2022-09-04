# ubuntu 업데이트
sudo apt-get update -y 
sudo apt-get upgrade -y
sudo reboot

# containerd 컨테이너 런타임 설치
sudo apt-get install containerd -y

sudo su -
containerd config default  /etc/containerd/config.toml

# 쿠버네티스 설치
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add
sudo apt-add-repository "deb http://apt.kubernetes.io/ kubernetes-xenial main"
sudo apt-get install kubeadm kubelet kubectl -y

# cgroup 드라이버 설정
cgroup="$(head -n1 /boot/firmware/cmdline.txt) cgroup_enable=cpuset cgroup_enable=memory cgroup_memory=1 swapaccount=1"
echo $cgroup | sudo tee /boot/firmware/cmdline.txt
## 안 되어서 /boot/firmware/cmdline.txt를 다음으로 변경 후 reboot
cgroup="$(head -n1 /boot/firmware/cmdline.txt) cgroup_enable=memory cgroup_memory=1 net.ifnames=0 dwc_otg.lpm_enable=0 console=ttyAMA0,115200 console=tty1 root=/dev/mmcblk0p2 rootfstype=ext4 elevator=deadline rootwait"
# cgroup_enable=memory cgroup_memory=1 net.ifnames=0 dwc_otg.lpm_enable=0 console=ttyAMA0,115200 console=tty1 root=/dev/mmcblk0p2 rootfstype=ext4 elevator=deadline rootwait
# 참고: https://ubuntu.com/tutorials/how-to-kubernetes-cluster-on-raspberry-pi#4-installing-microk8s


# swap 사용 비활성화
sudo sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
sudo swapoff -a

# containerd 설정
cat <<EOF | sudo tee /etc/modules-load.d/containerd.conf
overlay
br_netfilter
EOF

sudo modprobe overlay
sudo modprobe br_netfilter

cat <<EOF | sudo tee /etc/sysctl.d/99-kubernetes-cri.conf
net.bridge.bridge-nf-call-iptables  = 1
net.ipv4.ip_forward                 = 1
net.bridge.bridge-nf-call-ip6tables = 1
EOF

sudo sysctl --system
sudo systemctl restart containerd

# master node 설정
sudo kubeadm config images pull
sudo kubeadm init --pod-network-cidr=10.244.0.0/16 

# master node 쿠버네티스 설정 완료
kubeadm join 192.168.219.193:6443 --token pey7t5.ojbrs2uhduop1w6y \
        --discovery-token-ca-cert-hash sha256:2a83ff12fb87bb65282194ec4a88311b117e3274848381b40200d03b839f63ca

# 각 worker node에 master 와 통신하도록 설정
# worker1 
kubeadm join 192.168.219.193:6443 --token pey7t5.ojbrs2uhduop1w6y \
        --discovery-token-ca-cert-hash sha256:2a83ff12fb87bb65282194ec4a88311b117e3274848381b40200d03b839f63ca --node-name worker1
# worker2
kubeadm join 192.168.219.193:6443 --token pey7t5.ojbrs2uhduop1w6y \
        --discovery-token-ca-cert-hash sha256:2a83ff12fb87bb65282194ec4a88311b117e3274848381b40200d03b839f63ca --node-name worker2
# worker3
kubeadm join 192.168.219.193:6443 --token pey7t5.ojbrs2uhduop1w6y \
        --discovery-token-ca-cert-hash sha256:2a83ff12fb87bb65282194ec4a88311b117e3274848381b40200d03b839f63ca --node-name worker3
