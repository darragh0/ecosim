#!/usr/bin/env bash

if ! command -v mvn &> /dev/null
then
    echo -e "\e[1;91mMaven is not installed\e[0;0m"
    exit 1
fi

export LINES=$(tput lines)
export COLUMNS=$(tput cols)

clear
mvn exec:java -q
