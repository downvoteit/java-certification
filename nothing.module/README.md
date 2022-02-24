# Commands

## Change workdir
cd C:/Users/User/IdeaProjects/java-certification

## Clean
rm -rfv target/*

## Build with --module-path
javac -p target -d target/nothing.module nothing.module/com/nothing/*.java nothing.module/*.java

## Build with --module-source-path
javac --module-source-path . -d target -m nothing.module

## List
ll target
