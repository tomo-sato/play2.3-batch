package jp.dcworks.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import play.Play;

/**
 * コマンドライン用バッチ処理の起動クラス。
 * <p>コマンドラインより実行対象のクラスを指定し起動する。
 * <p>{@link jp.dcworks.batch.main.TestJob}クラスを起動する例（windows）：
 * <ul>
 * <li>play2.3-batchフォルダ直下より下記コマンドの実行
 * <pre>
 * &gt; activator stage
 * &gt; java -cp ./target/universal/stage/lib/* ^
 * -Dconfig.file=./target/universal/stage/conf/application.conf ^
 * jp.dcworks.batch.main.TestJob
 * </pre>
 * </li>
 * </ul>
 * @author tomo-sato
 */
public class JobExecutor {

	/** 起動引数：実行するバッチクラス名 */
	@Argument(index=0, metaVar="jobClass", required=true)
	private String jobClass;

	/** 起動引数：実行するバッチクラスに渡すパラメータ */
	@Argument(index=1, metaVar="arguments...", handler=StringArrayOptionHandler.class)
	private String[] arguments;

	/**
	 * コマンドライン起動{@code main}関数。
	 * @param args 起動引数
	 */
	public static void main(String[] args) {

		JobExecutor jobExecutor = new JobExecutor();

		CmdLineParser parser = new CmdLineParser(jobExecutor);
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			e.printStackTrace();
			return;
		}

		jobExecutor.execute();
	}

	/**
	 * バッチ処理起動メソッド。
	 * <p>Playアプリケーションの起動、バッチ実行クラスの呼び出しを行う。
	 */
	private void execute() {

		try {
			// playアプリケーション取得
			String applicationPath = new File("").getAbsolutePath();
			File applicationDir = new File(applicationPath);
			ClassLoader cl = this.getClass().getClassLoader();
			play.api.Application app = new play.api.DefaultApplication(applicationDir, cl, null, play.api.Mode.Prod());

			// playアプリケーション起動
			play.api.Play.start(app);

			// jobクラス取得
			@SuppressWarnings("unchecked")
			Class<? extends AppJob> clazz = (Class<? extends AppJob>) Play.application().classloader().loadClass(this.jobClass);

			// Jobインスタンス生成
			AppJob job = clazz.newInstance();

			// job呼び出し
			List<String> argList = null;
			if (this.arguments != null) {
				argList = new ArrayList<String>();
				argList.addAll(Arrays.asList(this.arguments));
			}
			job.call(argList);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			play.api.Play.stop();
		}
	}
}
