# baseFrame

# 1. 简介
&emsp;&emsp;`baseFrame`是基于MVP架构搭建的Android脚手架，旨在快速开发一个Android项目。

&emsp;&emsp;`baseFrame`采用Activity+Fragment模式，其中Activit里面只能做两件事：1.承载Fragment；2. 处理一些Activit级别的事务。所有的业务逻辑和View处理等操作都应该收敛到Fragment里面(如果能写在Presenter里面是最好的)。

&emsp;&emsp;在MVP架构下，主要分为三部分：1. View；2.Model；3.Presneter。而在`baseFrame`里面，View和Presenter两部分已经封装完毕，我们只需要在接入时实现对应的父类或者接口即可，Model需要我们自己来实现。在本repository中，我已经写了一个Demo以供参考。
# 2. 基本使用
&emsp;&emsp;我打算从两个方面来介绍`baseFrame`的基本使用。
### (1).View
&emsp;&emsp;View层主要有三个类，分别为：BaseActivity,BaseFragment,RecyclerViewFragment。

&emsp;&emsp;首先，如果我们新建一个Activity，要求该Activity必须继承于BaseActivity,同时要求实现三个方法，分别为：`getLayoutId`、`getFragmentContainerId`、`buildCurrentFragment`。这三个方法主要作用是用来加载一个Fragment。当然，也支持动态替换Fragment--`replaceFragment`。所以，在BaseActivity里面，不需要我们去管理Fragment。

&emsp;&emsp;其次，如果我们想要新建一个Fragment，要求该Fragment必须继承于BaseFragment。在BaseFragment里面，允许添加Presenter，所有的业务逻辑包括Model访问都应该放在Presenter里面。同时，为了大家使用`RecyclerView`方便，`baseFrame`里面还实现了一个`RecyclerViewFragment`,集体使用可以参考本repository。
### (2).Presenter
&emsp;&emsp;最后就是Presenter,它是整个脚手架里面最重要的部分，因为我们主要的逻辑都放在里面的。我们在分离Presenter时，有一个原则，一个View或者ViewGroup对应Presenter，这个Presenter处理有关这个View或者ViewGroup所有的逻辑。

&emsp;&emsp;Presenter有4个周期，生命周期的流程是：onCreate -> onBind -> onUnBind -> onDestroy,其中onCreate和onDestroy两个生命周期只能被回调一次。在BaseFragment`BaseRecyclerAdapter里面均已正确实现生命周期的回调，所以我们定义Fragment和RecyclerAdapter时，不用关心Presenter的生命周期。
# 3. 导入方式
&emsp;&emsp;TODO，有待更新。
# 4. 感谢
&emsp;&emsp;1. [MyTikTok](https://github.com/dongmin2002345/MyTikTok)

&emsp;&emsp;2. [blade](https://github.com/BeauteousJade/blade)
