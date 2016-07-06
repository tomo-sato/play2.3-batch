# play2.3-batch
play2.3-batch は、 Play framework 2.3 ベースで作成されたバッチアプリケーションベースです。



## ビルド方法
アプリケーションをクローンします。
```
git clone https://github.com/tomo-sato/play2.3-batch.git
```

アプリケーションフォルダ内に移動し、ビルドします。
```
cd play2.3-batch
activator stage
```

以上でセットアップ完了です。



## 使用方法
play2.3-batchフォルダに移動し、local.batに実行したいメイン処理を引数に指定しバッチを起動します。
```
local.bat jp.dcworks.batch.main.TestJob
```

サーバで下記ログが出力されれば成功です。
```
[info] play - Application started (Prod)
[info] j.d.b.m.TestJob - infoテスト
[warn] j.d.b.m.TestJob - warnテスト
[error] j.d.b.m.TestJob - errorテスト
[error] j.d.b.m.TestJob - errorテスト
java.lang.Exception: null
        at jp.dcworks.batch.main.TestJob.run(TestJob.java:37) [play2-3-batch.play2-3-batch-1.0.0.jar:1.0.0]
        at jp.dcworks.batch.AppJob.call(AppJob.java:29) [play2-3-batch.play2-3-batch-1.0.0.jar:1.0.0]
        at jp.dcworks.batch.JobExecutor.execute(JobExecutor.java:89) [play2-3-batch.play2-3-batch-1.0.0.jar:1.0.0]
        at jp.dcworks.batch.JobExecutor.main(JobExecutor.java:57) [play2-3-batch.play2-3-batch-1.0.0.jar:1.0.0]
```



## ドキュメント
* [play2.3-batch アプリケーションベース API仕様](https://tomo-sato.github.io/play2.3-batch/javadoc/)



### ライセンス
Copyright 2016 Tomomichi Sato This software is licensed under the Apache 2 license, quoted below.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
