package weibo4j;

import weibo4j.model.PostParameter;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class Messages {
	/*----------------------------用户接口----------------------------------------*/
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a href="http://open.weibo.com/wiki/2/users/show">users/show</a>
	 * @since JDK 1.5
	 */
	public void sendMessage(String uid) throws WeiboException {
		Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "messages/send.json",
				new PostParameter[] { new PostParameter("uid", uid) })
				.asJSONObject();
	}

}
