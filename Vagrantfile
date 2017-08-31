# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure("2") do |config|
  config.vm.box = "bento/centos-7.3"
  config.vm.boot_timeout = 240

  config.vm.provider "virtualbox" do |v|
	v.name = "sleuth_issue"
	v.memory = 4096
	v.cpus = 2
  end


  config.vm.provision "shell", path: "./config.sh"

  config.vm.network "forwarded_port", guest: 27017, host: 27017

end