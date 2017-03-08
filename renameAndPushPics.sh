#! /usr/bin/env bash

java -jar ./RenameAndMove.jar

# commmit and push to the remote
git add -A
git commit -m 'updated resources'
git push origin master

