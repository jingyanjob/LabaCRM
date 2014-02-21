<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<td><b>NEW TASK</b></td>
		</tr>
		<tr>
			<td>
			<form action="/LabaCRM/ct.dc?action=BatchStatusAdd">
				<table>
					<tr>
						<td>Task Name</td>
						<td><input name="taskname" /></td>
					</tr>
					<tr>
						<td>Appkey</td>
						<td><input name="appkey" /></td>
					</tr>
					<tr>
						<td>AppSecret</td>
						<td><input name="appsecret" /></td>
					</tr>
					<tr>
						<td>Redirect_URI</td>
						<td><input name="redirecturi" /></td>
					</tr>
					<tr>
						<td>User name</td>
						<td><input name="username" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input name="password" type="password"/></td>
					</tr>
					<tr>
						<td>Start Time</td>
						<td>
							<input name="starttimey" value="2012" style="width:50px"/>年
							<input name="starttimem" value="01" style="width:50px"/>月
							<input name="starttimed" value="01" style="width:50px"/>日
							<input name="starttimeh" value="12" style="width:50px"/>点
							<input name="starttimemin" value="00" style="width:50px"/>分
						
						</td>
					</tr>
					<tr>
						<td>Every ...</td>
						<td><input name="scheduletime" /> hour</td>
					</tr>
					<tr>
						<td>Weibo Text</td>
						<td><textarea rows="3" cols="50" name="wb"></textarea></td>
					</tr>
					<tr>
						<td>IMG</td>
						<td><input type="file"  name="wbimg" ></input></td>
					</tr>
					<tr>
						<td></td>
						<td align="center">
							<input type=submit value="NEW" />
						</td>
					</tr>
				</table>
				
				</form>
			</td>
		</tr>
		
		<tr>
			<td>
				<b>TASK LIST</b>
			</td>
		</tr>
		<tr>
			<td>
				task name 1 <input type=button value="stop" />
			</td>
		</tr>
		<tr>
			<td>
				task name 2 <input type=button value="stop" />
			</td>
		</tr>
	</table>

</body>
</html>