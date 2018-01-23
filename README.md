march action

# Etcd-Svr :

Etcd Distribute Manage System

# Install :
wget https://github.com/coreos/etcd/releases/download/v3.2.11/etcd-v3.2.11-linux-amd64.tar.gz

tar -zxvf etcd-v3.2.11-linux-amd64.tar.gz

mv etcd-v3.2.11-linux-amd64 etcd-v3.2.11

cd etcd-v3.2.11

[test@zoo1 etcd-v3.2.11]$ ./etcd --version
etcd Version: 3.2.11
Git SHA: 1e1dbb2
Go Version: go1.8.5
Go OS/Arch: linux/amd64
[test@zoo1 etcd-v3.2.11]$ ./etcdctl --version
etcdctl version: 3.2.11
API version: 2

# Start :
[test@zoo1 etcd-v3.2.11]$ ./etcd


# CONF :
mkdir etcd.conf


# etcd.conf1
name: ETCD-SINGLETON
data-dir: "./data"
listen-client-urls: "http://172.16.2.132:2379"
advertise-client-urls: "http://172.16.2.132:2379"
listen-peer-urls: "http://172.16.2.132:2380"
initial_advertise_peer_urls: "http://172.16.2.132:2380"

# ETCD OFFICAL

etcd:
  name:                        my-etcd-1
  listen_client_urls:          http://172.16.2.132:2379
  advertise_client_urls:       http://172.16.2.132:2379
  listen_peer_urls:            http://172.16.2.132:2380
  initial_advertise_peer_urls: http://172.16.2.132:2380



# etcd.conf2

name: ETCD-SINGLETON
data-dir: "./data"
listen-client-urls: "http://172.16.2.133:2379"
advertise-client-urls: "http://172.16.2.133:2379"
listen-peer-urls: "http://172.16.2.133:2380"
initial_advertise_peer_urls: "http://172.16.2.133:2380"


# ETCD OFFICAL

etcd:
  name:                        my-etcd-1
  listen_client_urls:          https://10.240.0.1:2379
  advertise_client_urls:       https://10.240.0.1:2379
  listen_peer_urls:            https://10.240.0.1:2380
  initial_advertise_peer_urls: https://10.240.0.1:2380



./etcd --config-file ./etcd.conf










