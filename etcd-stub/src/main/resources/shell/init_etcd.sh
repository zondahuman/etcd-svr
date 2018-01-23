#!/bin/sh

etcd_file="./etcd.conf"
etcd_data="./data"
install_file="./etcd-v3.2.11-linux-amd64.tar.gz"
install_directory="./etcd-v3.2.11"

if [ -f "$install_file" ]; then
	rm -rf "$install_file"
fi

if [ -d "$install_directory" ]; then
	rm -rf  "$install_directory"
fi
	
wget https://github.com/coreos/etcd/releases/download/v3.2.11/etcd-v3.2.11-linux-amd64.tar.gz

tar -zxvf etcd-v3.2.11-linux-amd64.tar.gz

mv etcd-v3.2.11-linux-amd64 etcd-v3.2.11

cd etcd-v3.2.11

echo `pwd`


if [ ! -f "$etcd_file" ]; then
	touch "$etcd_file" 
fi


if [ ! -d "$etcd_data" ]; then
	mkdir "$etcd_data"
fi

current_ip=`/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:"`

echo -e 'current_ip=', $current_ip

#etcd_conf_content="
#name: ETCD-SINGLETON\ndata-dir: \""$etcd_data"\"\nlisten-client-urls: \""http://"$current_ip":2379"\"\nadvertise-client-urls: \""http://"$current_ip":2379"\"\nlisten-peer-urls: \""http://"$current_ip":2380"\"\ninitial_advertise_peer_urls:  \""http://"$current_ip":2380"\"
#"
etcd_conf_content="name: ETCD-SINGLETON
data-dir: \""$etcd_data"\"
listen-client-urls: \""http://"$current_ip":2379"\"
advertise-client-urls: \""http://"$current_ip":2379"\"
listen-peer-urls: \""http://"$current_ip":2380"\"
initial_advertise_peer_urls:  \""http://"$current_ip":2380"\"
"


echo '' > etcd.conf

#echo -e $etcd_conf_content >  etcd.conf
echo -e "${etcd_conf_content}" >  etcd.conf

pidlist=`ps -ef | grep etcd | grep -v "grep" | awk '{print $2}'`
echo "Etcd Id list :$pidlist"  
if [ $pidlist ]; then
	sudo kill -9 $pidlist
fi

echo 'after kill ---------------start---sleep'
sleep 5
echo 'after---sleep--------------------------'

start_etcd="./etcd --config-file ./etcd.conf &"

eval $start_etcd



