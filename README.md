## aliyundisk

由于使用的操作系统是Linux，阿里云盘网页版下载有大小限制，使用spring-shell开发一个阿里云盘交互工具

### 启动项目

启动步骤需要配置阿里云盘的refresh_token，然后运行启动类即可

登录网页版阿里云盘从开发者工具中找到refresh_token 配置到此文件中src/main/resources/application-user.properties，如下图

![image-20220504182001466](https://gitee.com/aiden_fm/aliyundisk/raw/master/.images/README/image-20220504182001466.png)



### 基本功能

| 功能         | 命令                                                         | 备注                                                         |
| ------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 查看所有命令 | help                                                         | help                                                         |
| 查看目录详情 | listFile [parentFileId], lf [parentFileId], ll [parentFileId] | 默认查询根目录                                               |
| 下载文件     | df fileId filename [localPath]                               | localPath默认地址为项目目录下aliyunDw/                       |
| 搜索文件     | sf name [type] [category] [parentFileId]                     | type为match模糊查询,type为=精确查询 ,category为文件类型 video,image,folder,doc,audio |
| 上传文件     | uf localpath [parentFileId]                                  | localpath为本地文件路径   parentFileId网盘目录               |
| 新建文件夹   | cf name [parentFileId]                                       |                                                              |
| 关闭客户端   | quit , exit                                                  | 如果有下载任务则会等待下载完成后关闭，如果要强制关闭参考下面的命令 |
| 查询下载结果 | tr fileId                                                    |                                                              |
| 停止下载     | kill [fileId]                                                | interrupt对应fileId的下载任务， 不填fileId则强制关闭所有任务 |



#### 后续功能

#### 异步分块上传

当前版本上传功能的实现是整个文件同步上传，没有使用阿里云盘分块上传的功能，后期加上异步分块上传功能

#### 文件（夹）删除恢复功能

#### 用户登录功能

目前仅支持refresh_token登录，后期新增扫码登录，短信登录，帐号登录

#### 文件分享功能
