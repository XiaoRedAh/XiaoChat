最终解决：https://blog.csdn.net/Seky_fei/article/details/116802863

JDK8自含javafx的软件包，而更高版本的jdk没有自带javafx，需要额外下载javafx的maven依赖

但是我装的是openJDK8，这个坑壁没有带javafx包！！！！然后就自然而然地根据IDEA的提示安装JavaFx的依赖，结果可想而知，jdk版本太低不兼容，又报错了
接着根据网上的指导，把项目版本拉高到JDK17，结果又报错“找不到javaFx运行组件”，于是又引进module-info.java这个文件，好不容易跑起来了，结果发现背景图片一个都加载不出来！！！！

最后找了好久，才发现，原来要装Oracle JDK8！！！！