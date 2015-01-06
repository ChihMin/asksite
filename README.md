# JAVA Servlet with MVC Framework
---
由於為了測試，我直接把網頁做起來了

不過也只是寫一些簡單的javascript

由於Server都是送json檔

其實可以很輕鬆的用ajax的方式去做網頁的呈現

還有我已經deploy上去了

以下是網址：http://chihmin-ask.appspot.com/

如果要收某個Query Keywords 的 comments

他的ＵＲＬ：http://chihmin-ask.appspot.com/comments/YOUR_KEYWORD

直接對這樣的格式做Rest GET Request就可以了

然後如果要在本地端跑起來的話

首先去『pom.xml』的第130行那邊

助教原本的code應該沒有那一段

記得把一些網址的參數也要加上去

要先去看你在區域網路的IP是什麼

然後去 js/index.js 裡面把第一行的URL改掉

如果你的server開起來是 http://localhost:8080

請把localhost 改成你所查詢到的ＩＰ

貼到JS檔

這樣網頁就能動了（汗＝＝）

如果你覺得我server開起來太麻煩 ....

直接來這裡玩：http://chihmin-ask.appspot.com/

然後剩下的自己研究吧ＸＤ




