<html>
<head>
  <title> Data </title>
</head>

<body>

<?php
$con = mysql_connect("localhost","root","76OKfYcb44");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("blog", $con);

$sql="INSERT INTO blogposts (title, content)
VALUES
('$_POST[titleofpost]','$_POST[contentofpost]')";

if (!mysql_query($sql,$con))
  {
  die('Error: ' . mysql_error());
  }
echo "1 record added";

mysql_close($con)
?>
</body>
</html>
</body>
</html>
