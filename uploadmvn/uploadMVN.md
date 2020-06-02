
### 常用地址：
>官网地址：https://issues.sonatype.org
>
>

### 1.0 注册并且创建工单：
    
    0.官网地址：https://issues.sonatype.org
    1.groupId的审核，https://github.com/a982338665/lf-execl-easy时，建议写为com.github.a982338665保证唯一
    2.跟审核人员的不断沟通，按提醒完成相关操作，审核状态由OPEN -> Resolved
    
### 2.0 配置MVN的Settings.xml信息：conf/settings.xml的servers标签下

     <server>
        <id>sonatype-nexus-snapshots</id>
        <username>https://issues.sonatype.org的账号</username>
        <password>https://issues.sonatype.org的密码</password>
      </server>
      <server>
        <id>sonatype-nexus-staging</id>
        <username>https://issues.sonatype.org的账号</username>
        <password>https://issues.sonatype.org的密码</password>
      </server>

### 3.0 配置项目下的pom.xml：
    
    <parent>
        <groupId>org.sonatype.oss</groupId>
        <artifactId>oss-parent</artifactId>
        <version>7</version>
    </parent>
    
    <licenses>
        <license>
            <name>The Apache Software License, Version 2.0</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
            <distribution>repo</distribution>
        </license>
    </licenses>
    
    <!--    需要替换的内容-->
    <scm>
        <url>https://github.com/a982338665/lf-execl-easy</url>
        <connection>https://github.com/a982338665/lf-execl-easy.git</connection>
        <developerConnection>https://github.com/a982338665/lf-execl-easy</developerConnection>
    </scm>
    <developers>
        <developer>
            <name>luofeng</name>
            <email>982338665@qq.com</email>
            <url>https://github.com/a982338665/lf-execl-easy</url>
        </developer>
    </developers>
    
### 4.0 GPG环境，用来对上传的文件进行加密和签名，保证你的jar包不被篡改:
    
    1.下载地址：
        windows(二进制安装，安装后将可执行命令添加至系统环境变量中)：https://www.gpg4win.org/
        linux: https://www.gnupg.org/download/
    2.相关命令：
        ·安装是否成功： gpg –version 
        ·生成密钥对：gpg --gen-key
                    D:\install-soft\Gpg4win\bin>gpg --gen-key
                    gpg (GnuPG) 2.2.19; Copyright (C) 2019 Free Software Foundation, Inc.
                    This is free software: you are free to change and redistribute it.
                    There is NO WARRANTY, to the extent permitted by law.
                    
                    Note: Use "gpg --full-generate-key" for a full featured key generation dialog.
                    
                    GnuPG needs to construct a user ID to identify your key.
                    
                    Real name: a982338665
                    Email address: 982338665@qq.com
                    You selected this USER-ID:
                        "a982338665 <982338665@qq.com>"
                    
                    Change (N)ame, (E)mail, or (O)kay/(Q)uit? o
                    We need to generate a lot of random bytes. It is a good idea to perform
                    some other action (type on the keyboard, move the mouse, utilize the
                    disks) during the prime generation; this gives the random number
                    generator a better chance to gain enough entropy.
                    We need to generate a lot of random bytes. It is a good idea to perform
                    some other action (type on the keyboard, move the mouse, utilize the
                    disks) during the prime generation; this gives the random number
                    generator a better chance to gain enough entropy.
                    gpg: key 6A08A45BDF4C5202 marked as ultimately trusted
                    gpg: directory 'C:/Users/Administrator/AppData/Roaming/gnupg/openpgp-revocs.d' created
                    gpg: revocation certificate stored as 'C:/Users/Administrator/AppData/Roaming/gnupg/openpgp-revocs.d\29851CD296284B502C608DE06A08A45BDF4C5202.rev'
                    public and secret key created and signed.
                    
                    pub   rsa2048 2020-06-02 [SC] [expires: 2022-06-02]
                          29851CD296284B502C608DE06A08A45BDF4C5202
                    uid                      a982338665 <982338665@qq.com>
                    sub   rsa2048 2020-06-02 [E] [expires: 2022-06-02]
                    生成的时候会弹框要求输密码：打包上传的时候需要它，保存好
                    以上完成后，【6A08A45BDF4C5202】就是申请的key
        ·查看公钥：gpg --list-keys
                    D:\install-soft\Gpg4win\bin>gpg --list-keys
                    C:/Users/Administrator/AppData/Roaming/gnupg/pubring.kbx
                    --------------------------------------------------------
                    pub   rsa2048 2020-06-02 [SC] [expires: 2022-06-02]
                          29851CD296284B502C608DE06A08A45BDF4C5202
                    uid           [ultimate] a982338665 <982338665@qq.com>
                    sub   rsa2048 2020-06-02 [E] [expires: 2022-06-02]
                    公钥为：【29851CD296284B502C608DE06A08A45BDF4C5202】
        ·将公钥发布到PGP密钥服务器-linux：将我们的公钥发布给官方，让其能够解析我们本地用私钥加密后的数据
            gpg --keyserver hkp://pool.sks-keyservers.net --send-keys 公钥ID
        ·将公钥发布到PGP密钥服务器-windows：gpg --keyserver hkp://keyserver.ubuntu.com:11371 --send-keys 5C1845F3
        ·查询公钥是否发布成功：
            gpg --keyserver hkp://pool.sks-keyservers.net --recv-keys 公钥ID

### 5.0
