package weibo4j;

import java.util.List;

import weibo4j.model.Paging;
import weibo4j.model.PostParameter;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class Friendships {
	/*----------------------------关系接口----------------------------------------*/
	/**
	 * 获取用户的关注列表
	 * 
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends">friendships/friends</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendsByID(String id) throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends.json",
				new PostParameter[] { new PostParameter("uid", id) }));
	}
	
	public List<User> getFriendsByID(String uid, Integer count, Integer cursor)
		throws WeiboException {
	return User.constructUser(Weibo.client.get(
			WeiboConfig.getValue("baseURL") + "friendships/friends.json",
			new PostParameter[] { new PostParameter("uid", uid),
					new PostParameter("count", count.toString()),
					new PostParameter("cursor", cursor.toString()) }));
	}
	/**
	 * FriendChainFollowers
	 * 
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends">friendships/friends</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendChainFollowers(String uid, Integer count, Integer page)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends_chain/followers.json",
				new PostParameter[] { new PostParameter("uid", uid),
					new PostParameter("count", count.toString()),
					new PostParameter("page", page.toString()) }));
	}
	/**
	/**
	 * 获取互相关注用户的关注列表
	 * 
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends">friendships/friends</a>
	 * @since JDK 1.5
	 */
	public List<User> getBilateral(String uid, Integer count, Integer page)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends/bilateral.json",
				new PostParameter[] { new PostParameter("uid", uid),
					new PostParameter("count", count.toString()),
					new PostParameter("page", page.toString()) }));
	}
	/**
	 * 获取用户的关注列表
	 * 
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends">friendships/friends</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendsByScreenName(String screen_name)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * 获取两个用户之间的共同关注人列表
	 * 
	 * @param uid
	 *            需要获取共同关注关系的用户UID
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/in_common">friendships/friends/in_common</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendsInCommon(String uid) throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/in_common.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 获取两个用户之间的共同关注人列表
	 * 
	 * @param uid
	 *            需要获取共同关注关系的用户UID
	 * @param suid
	 *            需要获取共同关注关系的用户UID，默认为当前登录用户
	 * @param count
	 *            单页返回的记录条数，默认为50
	 * @param page
	 *            返回结果的页码，默认为1
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/in_common">friendships/friends/in_common</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendsInCommon(String uid, String suid, Paging page)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/in_common.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("suid", suid), }, page));
	}

	/**
	 * 获取用户的双向关注列表，即互粉列表
	 * 
	 * @param uid
	 *            需要获取双向关注列表的用户UID
	 * @return list of the user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral">friendships/friends/bilateral</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendsBilateral(String uid) throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 获取用户的双向关注列表，即互粉列表
	 * 
	 * @param uid
	 *            需要获取双向关注列表的用户UID
	 * @param count
	 *            单页返回的记录条数，默认为50。
	 * @param page
	 *            返回结果的页码，默认为1。
	 * @param sort
	 *            排序类型，0：按关注时间最近排序，默认为0。
	 * @return list of the user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral">friendships/friends/bilateral</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendsBilateral(String uid, Integer sort, Paging page)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("sort", sort.toString()) }, page));
	}

	/**
	 * 获取用户双向关注的用户ID列表，即互粉UID列表
	 * 
	 * @param uid
	 *            需要获取双向关注列表的用户UID
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral/ids">friendships/friends/bilateral/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsBilateralIds(String uid) throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral/ids.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 获取用户双向关注的用户ID列表，即互粉UID列表
	 * 
	 * @param uid
	 *            需要获取双向关注列表的用户UID
	 * @param count
	 *            单页返回的记录条数，默认为50。
	 * @param page
	 *            返回结果的页码，默认为1。
	 * @param sort
	 *            排序类型，0：按关注时间最近排序，默认为0。
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral/ids">friendships/friends/bilateral/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsBilateralIds(String uid, Integer sort, Paging page)
			throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral/ids.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("sort", sort.toString()) }, page));
	}
	/**
	 * 获取用户双向关注的用户ID列表，即互粉UID列表
	 * 
	 * @param uid
	 *            需要获取双向关注列表的用户UID
	 * @param count
	 *            单页返回的记录条数，默认为50。
	 * @param page
	 *            返回结果的页码，默认为1。
	 * @param sort
	 *            排序类型，0：按关注时间最近排序，默认为0。
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral/ids">friendships/friends/bilateral/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsBilateralIds(String uid, int count, Integer sort, Paging page)
			throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral/ids.json",
					new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("count", count),
						new PostParameter("sort", sort.toString()) }, page)
						
				);
	}

	/**
	 * 获取用户关注的用户UID列表
	 * 
	 * @param uid
	 *            需要查询的用户UID
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIds(String uid) throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 获取用户关注的用户UID列表
	 * 
	 * @param uid
	 *            需要查询的用户UID
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIdsByName(String screen_name)
			throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * 获取用户关注的用户UID列表
	 * 
	 * @param uid
	 *            需要查询的用户UID
	 * @param count
	 *            单页返回的记录条数，默认为500，最大不超过5000
	 * @param cursor
	 *            返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIds(String uid, Integer count, Integer cursor)
			throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json", new PostParameter[] {
						new PostParameter("uid", uid),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * 获取用户关注的用户UID列表
	 * 
	 * @param screen_name
	 *            需要查询的用户昵称
	 * @param count
	 *            单页返回的记录条数，默认为500，最大不超过5000
	 * @param cursor
	 *            返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIdsByName(String screen_name, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json", new PostParameter[] {
						new PostParameter("screen_name", screen_name),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * 批量获取当前登录用户的关注人的备注信息
	 * 
	 * @param uids
	 *            需要获取备注的用户UID，用半角逗号分隔，最多不超过50个
	 * @return list of user's remark
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/remark_batch">friendships/friends/remark_batch</a>
	 * @since JDK 1.5
	 */
	public List<User> getRemark(String uids) throws WeiboException {
		List<User> users = User.constructUsers(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/remark_batch.json",
				new PostParameter[] { new PostParameter("uids", uids) }));
		return users;
	}

	/**
	 * 获取用户的粉丝列表
	 * 
	 * @param screen_name
	 *            需要查询的用户昵称
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public List<User> getFollowersByName(String screen_name)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * 获取用户的粉丝列表
	 * 
	 * @param screen_name
	 *            需要查询的用户昵称
	 * @param count
	 *            单页返回的记录条数，默认为500，最大不超过5000
	 * @param cursor
	 *            返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public List<User> getFollowersByName(String screen_name, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] {
						new PostParameter("screen_name", screen_name),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * 获取用户的粉丝列表
	 * 
	 * @param screen_name
	 *            需要查询的用户昵称
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public List<User> getFollowersById(String uid) throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 获取用户的粉丝列表
	 * 
	 * @param screen_name
	 *            需要查询的用户昵称
	 * @param count
	 *            单页返回的记录条数，默认为500，最大不超过5000
	 * @param cursor
	 *            返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public List<User> getFollowersById(String uid, Integer count, Integer cursor)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * 获取用户粉丝的用户UID列表
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsById(String uid) throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 获取用户粉丝的用户UID列表
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @param count
	 *            单页返回的记录条数，默认为500，最大不超过5000
	 * @param cursor
	 *            返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsById(String uid, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * 获取用户粉丝的用户UID列表
	 * 
	 * @param screen_name
	 *            需要查询的用户昵称
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsByName(String screen_name)
			throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * 获取用户粉丝的用户UID列表
	 * 
	 * @param screen_name
	 *            需要查询的用户ID
	 * @param count
	 *            单页返回的记录条数，默认为500，最大不超过5000
	 * @param cursor
	 *            返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsByName(String screen_name, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructIds(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] {
						new PostParameter("screen_name", screen_name),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * 获取用户的活跃粉丝列表
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return list of user's id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/active">friendships/followers/active</a>
	 * @since JDK 1.5
	 */
	public List<User> getFollowersActive(String uid) throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/active.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 获取用户的活跃粉丝列表
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @param count
	 *            返回的记录条数，默认为20，最大不超过200。
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/active">friendships/followers/active</a>
	 * @since JDK 1.5
	 */
	public List<User> getFollowersActive(String uid, Integer count)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/active.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("count", count.toString()) }));
	}

	/**
	 * 获取当前登录用户的关注人中又关注了指定用户的用户列表
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends_chain/followers">friendships/friends_chain/followers</a>
	 * @since JDK 1.5
	 */
	public List<User> getFriendsChainFollowers(String uid)
			throws WeiboException {
		return User.constructUser(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends_chain/followers.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * 关注一个用户
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/create">friendships/create</a>
	 * @since JDK 1.5
	 */
	public User createFriendshipsById(String uid) throws WeiboException {
		return new User(Weibo.client.post(
				WeiboConfig.getValue("baseURL") + "friendships/create.json",
				new PostParameter[] { new PostParameter("uid", uid) })
				.asJSONObject());
	}

	/**
	 * 关注一个用户
	 * 
	 * @param screen_name
	 *            需要查询的用户screen_name
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/create">friendships/create</a>
	 * @since JDK 1.5
	 */
	public User createFriendshipsByName(String screen_name)
			throws WeiboException {
		return new User(Weibo.client.post(
				WeiboConfig.getValue("baseURL") + "friendships/create.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }).asJSONObject());
	}

	/**
	 * 取消关注一个用户
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/destroy">friendships/destroy</a>
	 * @since JDK 1.5
	 */
	public User destroyFriendshipsDestroyById(String uid) throws WeiboException {
		return new User(Weibo.client.post(
				WeiboConfig.getValue("baseURL") + "friendships/destroy.json",
				new PostParameter[] { new PostParameter("uid", uid) })
				.asJSONObject());
	}

	/**
	 * 取消关注一个用户
	 * 
	 * @param screen_name
	 *            需要查询的用户screen_name
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/destroy">friendships/destroy</a>
	 * @since JDK 1.5
	 */
	public User destroyFriendshipsDestroyByName(String screen_name)
			throws WeiboException {
		return new User(Weibo.client.post(
				WeiboConfig.getValue("baseURL") + "friendships/destroy.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }).asJSONObject());
	}
}
