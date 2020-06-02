
### 常用地址：
>官网地址：https://issues.sonatype.org
>GPG环境下载地址：https://www.gnupg.org/download/
>GPG环境下载地址：https://www.gnupg.org/download/
>参考地址：https://www.jianshu.com/p/354f66ed4f89

### 1.0 注册并且创建工单：
    
    0.官网地址：https://issues.sonatype.org
    1.groupId的审核，https://github.com/a982338665/lf-execl-easy时，建议写为com.github.a982338665保证唯一
    2.跟审核人员的不断沟通，按提醒完成相关操作，审核状态由OPEN -> Resolved
    
### 2.0 配置MVN的Settings.xml信息：conf/settings.xml的servers标签下

     <servers>
         <server>
             <id>oss</id>
             <username>用户名</username>
             <password>密码</password>
         </server>
     </servers>

### 3.0 配置项目下的pom.xml：配置代码发布器：
    
    <!-- pom.xml 中必须包括：name、description、url、licenses、developers、scm 等基本信息，使用了 Maven 的 profile 功能，
    只有在 release 的时候才创建源码包、文档包、使用 GPG 进行数字签名。 -->
    
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
    
        <groupId>com.github.simonalong</groupId>
        <artifactId>mikilin</artifactId>
        <packaging>jar</packaging>
        <version>1.4.0</version>
    
        <name>Mikilin</name>
        <url>https://github.com/SimonAlong/Mikilin</url>
        <description>对象属性核查工具</description>
    
        <licenses>
            <license>
                <name>The Apache Software License, Version 2.0</name>
                <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
            </license>
        </licenses>
    
        <developers>
            <developer>
                <name>zhouzhenyong</name>
                <email>simonalongs@126.com</email>
                <roles>
                    <role>developer</role>
                </roles>
            </developer>
        </developers>
    
        <scm>
            <connection>scm:git:git@github.com:SimonAlong/Mikilin.git</connection>
            <developerConnection>scm:git:git@github.com:SimonAlong/Mikilin.git</developerConnection>
            <url>git@github.com:SimonAlong/Mikilin.git</url>
        </scm>
    
        <properties>
            <maven.compiler.source>1.8</maven.compiler.source>
            <maven.compiler.target>1.8</maven.compiler.target>
        </properties>
    
        <dependencies>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.0</version>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.47</version>
            </dependency>
    
            <!--spock测试框架-->
            <dependency>
                <groupId>org.spockframework</groupId>
                <artifactId>spock-core</artifactId>
                <version>1.2-groovy-2.4</version>
                <scope>test</scope>
                <exclusions>
                    <exclusion>
                        <artifactId>groovy-all</artifactId>
                        <groupId>org.codehaus.groovy</groupId>
                    </exclusion>
                </exclusions>
            </dependency>
    
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
                <version>1.7.12</version>
            </dependency>
            <dependency>
                <groupId>org.codehaus.groovy</groupId>
                <artifactId>groovy-all</artifactId>
                <version>2.4.8</version>
                <scope>compile</scope>
            </dependency>
        </dependencies>
    
        <build>
            <plugins>
                <!-- Source -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-source-plugin</artifactId>
                    <version>2.1</version>
                    <configuration>
                        <attach>true</attach>
                    </configuration>
                    <executions>
                        <execution>
                            <phase>compile</phase>
                            <goals>
                                <goal>jar</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
    
                <!-- Javadoc -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-javadoc-plugin</artifactId>
                    <version>2.10.4</version>
                    <configuration>
                        <show>private</show>
                        <nohelp>true</nohelp>
                        <charset>UTF-8</charset>
                        <encoding>UTF-8</encoding>
                        <docencoding>UTF-8</docencoding>
                    </configuration>
                    <executions>
                        <execution>
                            <phase>package</phase>
                            <goals>
                                <goal>jar</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
    
                <!-- GPG -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-gpg-plugin</artifactId>
                    <version>1.6</version>
                    <executions>
                        <execution>
                            <id>sign-artifacts</id>
                            <phase>verify</phase>
                            <goals>
                                <goal>sign</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
    
        <distributionManagement>
            <snapshotRepository>
                <id>oss</id>
                <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
            </snapshotRepository>
            <repository>
                <id>oss</id>
                <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
            </repository>
        </distributionManagement>
    
    </project>
    
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
        ·将公钥发布到GPG密钥服务器-linux：将我们的公钥发布给官方，让其能够解析我们本地用私钥加密后的数据
            gpg --keyserver hkp://pool.sks-keyservers.net --send-keys 公钥ID
        ·将公钥发布到PGP密钥服务器-windows：gpg --keyserver hkp://keyserver.ubuntu.com:11371 --send-keys 5C1845F3
        ·查询公钥是否发布成功：
            gpg --keyserver hkp://pool.sks-keyservers.net --recv-keys 公钥ID

### 5.0
