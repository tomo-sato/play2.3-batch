package jp.dcworks.batch.main;

import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import jp.dcworks.batch.AppJob;

/**
 * バッチ処理テストクラス。
 * <p>バッチ疎通確認等に使用する。
 * @author tomo-sato
 */
public class TestJob extends AppJob {
	
	/** Logger定義 */
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(AppJob.class.getName());

	/**
	 * バッチ処理テストクラスコンストラクタ。
	 */
	public TestJob() {}

	/**
	 * バッチメイン処理。
	 * @see jp.dcworks.batch.AppJob#run(java.util.List)
	 */
	@Override
	protected void run(List<String> args) {

		// ログ出力テスト
		LOGGER.debug("debugテスト");
		LOGGER.info("infoテスト");
		LOGGER.warn("warnテスト");
		LOGGER.error("errorテスト");
		LOGGER.error("errorテスト", new Exception());
	}
}
