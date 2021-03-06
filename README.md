# 安卓开发

武汉理工大学个性课安卓应用开发的作业

## 目录

此项目下的每个文件夹都是一个安卓应用。

### job1

```
1.搭建Android开发环境
2.建立一个包含两个Activity的App，App启动后默认展示登录Activity，登陆成功后跳转至列表Activity展示歌曲信息。

具体要求：
1.所有界面图标大小均为100*100
2.用户名和密码输入框（EditText）均为单行输入框（layout中设置android:singleLine="true"）
3.用户名和密码输入框，需有如图所示的默认提示，输入时提示自动消失（layout中使用android:hint）。
4.密码输入框需设置为数字密码输入模式（layout中设置android:inputType="numberPassword"）
5.登录Activity中点击退出按钮，则关闭Activity并退出程序
6.登录Activity中点击登录按钮时，检测输入密码是否为123456（不检测用户名），正确则跳转至列表Activity，错误则Toast"密码错误，请重试"的信息。
7.跳转至列表Activity时，需将输入的用户名传过来，并在列表Activity上方显示“欢迎您，XXX！”的信息（此处XXX为用户填写的用户名）
8.列表Activity中点击退出按钮，则返回登录界面
9.列表Activity中通过自定义ListView展示歌曲信息。
10.点击列表任意项，Toast显示歌曲的名称。
```

### job2

```
1.在作业1的基础上进行修改
2.在登录界面增加一个复选框，实现记住用户名功能。登陆按钮点击时，若该复选框选中，则登陆成功后，将该用户名保存下来；若该复选框未选中，则登陆成功后，将原来保存的用户名清除。登录界面打开时，默认用户名输入框读入保存的用户名，若没有则用户名输入框填空。界面原型如下图。
3.在列表界面实现动态注册监听耳机的插入与拔出，并toast相关提示。
4.原歌曲对象增加一个成员信息“文件路径”，点击对应item时，toast显示其文件路径(歌曲信息相关修改参考我给出作业2例子中的代码，我已实现)。
5.将原工程中的音乐信息去除，通过ContentProvider 获取本机音乐信息，并填充listview。

具体要求：
1.所有界面图标大小均为100*100
2.耳机插入时Toast"耳机已连接"的信息，耳机拔出时Toast"耳机已断开"的信息。
3.耳机相关广播为android.intent.action.HEADSET_PLUG
4.取本机歌曲信息uri为MediaStore.Audio.Media. EXTERNAL_CONTENT_URI
5.媒体库中无法获取专辑图片，所有歌曲统一使用默认图片做封面
```

### job3

```
1.在作业2的基础上进行修改
2将上次作业实现的动态注册监听耳机的插入与拔出并toast相关提示，修改为动态注册监听耳机的插入与拔出并在通知栏给出相关提示
3.实现歌曲列表点击对应item时，播放相应歌曲
4.列表上方添加相应控件，实现播放、暂停、停止和进度条等功能。

具体要求：
1.所有界面图标大小均为100*100
2.耳机插入时通知"耳机已连接"的信息，耳机拔出时同志"耳机已断开"的信息。点击去除通知，无跳转。
3.耳机相关广播为android.intent.action.HEADSET_PLUG
4.取本机歌曲信息uri为MediaStore.Audio.Media. EXTERNAL_CONTENT_URI
```

### Muse

```
在前面的基础上，自选方向，对播放器的功能进行扩展，如实现歌曲列表、歌词显示、后台播放等。
```
登录界面：
![](https://github.com/virlaser/AndroidDev/blob/master/screenshot/1.png?raw=true)

歌曲列表：
![](https://github.com/virlaser/AndroidDev/blob/master/screenshot/2.png?raw=true)

歌曲播放:
![](https://github.com/virlaser/AndroidDev/blob/master/screenshot/3.png?raw=true)

耳机插入通知：
![](https://github.com/virlaser/AndroidDev/blob/master/screenshot/4.png?raw=true)


