#!/bin/bash

# 编译Java程序
javac Eat.java

# 运行Java程序，并传递参数到命令行中
java Eat $1

# 删除字节码文件
rm Eat.class
