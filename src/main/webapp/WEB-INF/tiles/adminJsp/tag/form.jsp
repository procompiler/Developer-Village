<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<h1>태그 추가</h1>
<form action="../tag/add" method="post" enctype="multipart/form-data">
태그 이름 : <input type="text" name="name"><br>
태그 색상 : <input type="color" name="tagColor"><br>
폰트 색상 : <input type="color" name="fontColor"><br>
태그 사진 : <input type="file" name="photoFile"><br>
<button class="btn btn-primary">등록</button>
</form>
