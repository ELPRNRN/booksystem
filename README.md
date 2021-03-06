1.系统功能分析

1.1.任务概述
经过详细调查，加深了对先行图书管理业务的深刻认识，与此同时，将整个图书管理系统的业务流程抽象描述如下：
1.新的图书购进后，管理员将图书信息注册到系统中
2.注册完之后，管理员将图书上架到书架供学生借阅
3.如某学生需要借阅图书，则在系统中登录并查找想要借的书，确认借阅后系统将借阅记录记录到数据库中
4.学生借阅图书后，应该在规定时间内归还图书；如果图书过期未还，则学生无法进行借还操作，需要找管理员进行相应操作
5.管理员可以对图书和读者进行管理，包括添加图书和读者、更新图书和读者信息、删除图书和读者信息



1.2.功能需求
经过用户调查，在业务流程的基础上，可以确定系统设计必须达到的目标。
以下是图书管理系统必须具备的功能：
1.新进图书的注册功能：对于购进的新书，系统必须具备图书信息资料的录入功能
2.图书的信息修改功能：当图书信息资料发生变化时，应能够及时对数据进行修改和补充。
3.图书的删除功能：当图书馆中不再提供某图书时，管理员从系统中删除该图书的信息
4.读者注册功能：读者需要在系统中注册帐号才能够使用系统
5.读者信息更新功能：系统需要向读者和管理员提供更新读者信息的功能
6.读者删除功能：当某读者不再使用系统时，管理员能够从系统中删除该读者的信息
7.查询读者功能：管理员能够通过读者号查询特定读者的信息
8.借还图书功能：读者能够通过系统借阅和归还图书；当读者有书过期未还时，系统会锁定读者帐号，读者无法进行借还操作
9.查询图书功能：读者和管理员能够通过各种信息（书号、书名、作者名、出版社名等）在系统中对图书进行模糊搜索
10.权限登录功能：读者和管理员登入到系统时，系统会根据它们的权限分别提供功能




2.数据库设计
  整个系统所包括的信息有图书信息、读者信息、图书借出与归还信息、管理员信息、作者信息、出版社信息。可将信息抽象为下列数据项和结构：
（1）管理员信息（姓名，密码）
（2）读者信息（姓名，读者ID，类型，性别，密码）
（3）图书信息（图书ID，书名，价格，类型，作者，出版社，剩余数量，简介）
（4）借出与归还信息（读者ID，图书ID，借出时间，到期时间，是否逾期）
（5）作者信息（姓名，国籍）
（6）出版社（名字，地址）

3.开发环境
开发系统：Windows 10
后端开发语言：Java
框架：MVC
前端设计：Java Swing+BeautyEye
开发平台：Eclipse 2019
版本管理工具：Github
数据库：Sql Server


