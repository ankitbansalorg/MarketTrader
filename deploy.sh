#!/bin/bash
TOMCAT=$HOME/Softwares/apache-tomcat-6.0.33

echo Stopping Tomcat
ps -ef | grep tomcat |grep -v grep| awk '{ print $2}' |xargs kill -9
rm -rf $TOMCAT/logs/*
rm -rf $TOMCAT/work/Catalina/localhost/*
rm -rf $TOMCAT/webapps/MarketTrader*
echo Tomcat Stopped

mvn clean package
if [[ $? != 0 ]] ; then
    exit $?
fi

cp target/MarketTrader-1.0.war $TOMCAT/webapps/

echo Starting Tomcat
sh $TOMCAT/bin/startup.sh

