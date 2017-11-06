//验证码star
function genTimestamp() {
	var time = new Date();
	return time.getTime();
}
function changeCode() {
	$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
}
$(document).ready(function() {
	changeCode();
	$("#codeImg").bind("click", changeCode);
});
//验证码end