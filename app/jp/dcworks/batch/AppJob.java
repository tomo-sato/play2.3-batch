package jp.dcworks.batch;

import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * バッチ処理の基底クラス。
 * <p>バッチ処理を実装する場合、このクラスを継承する。
 * バッチの起動は、{@link jp.dcworks.batch.JobExecutor#execute()}メソッドより呼び出される。
 * @author tomo-sato
 */
public abstract class AppJob {
	
	/** Logger定義 */
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(AppJob.class.getName());

	/**
	 * バッチ処理の呼び出し処理。
	 * <p>{@link jp.dcworks.batch.JobExecutor#execute()}メソッドより呼び出される。
	 * @param args 起動引数
	 */
	public final void call(List<String> args) {
		try {
			if(init()) {
				before();
				run(args);
				after();
			}
		} catch (Exception e) {
			LOGGER.error("バッチ処理実行中にエラーが発生しました。", e);
		} finally {
			_finally();
		}
	}

	/**
	 * バッチ処理の初期化。
	 * 初期化処理が必要な場合、このメソッドを継承する。
	 * <p>初期化に失敗した場合、{@link #before()}、{@link #run(List)}、{@link #after()}メソッドの処理は行われない。
	 * @return true：初期化成功、false：失敗。
	 */
	protected boolean init() {
		return true;
	}

	/**
	 * バッチ処理の前処理。
	 * 前処理が必要な場合、このメソッドを継承する。
	 */
	protected void before() {}

	/**
	 * バッチ処理のメイン処理。
	 * 継承クラスはバッチメイン処理として、このメソッドを実装する。
	 * @param args 起動引数
	 * @throws Exception 例外
	 */
	protected abstract void run(List<String> args) throws Exception;

	/**
	 * バッチ処理の後処理。
	 * 後処理が必要な場合、このメソッドを継承する。
	 */
	protected void after() {}

	/**
	 * バッチ処理のメモリ解放等。
	 * バッチ処理のメモリ解放等行う場合、このメソッドを継承する。
	 */
	protected void _finally() {}
}
