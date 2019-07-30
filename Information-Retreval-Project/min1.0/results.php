<?php

include_once('includes/Similarity.php');
include_once('includes/database.php');


	$query=$_POST['query'];
	
	$sim->setQuery($query);
	$arrResults=$sim->search();
	$result="";
	foreach($arrResults as $k=>$v){
		
		$row=$db->query("select * from  min_article  where id=".$k);
		$rowRes=$db->fetch_array($row);
		$result.='<a href="'.$rowRes['url'].'">'.$rowRes['title'].'</a> <br/>';
		$resultSummary=$rowRes['text'];
		$resultSummary=preg_replace("/(http(s)?:\/\/)?([\w-]+\.)+[\w-]+(\/[\w- \.\/\\?\%\&\=]*)?+(\/)?/"," ",$resultSummary);
		$resultSummary= preg_replace("/<(\/)?(\w+)([^>]*)>/"," ",$resultSummary);
		$result.='<p style="color:#515151">'.substr($resultSummary,0,400).'....</p>';
		$result.='<a class="btn btn-success btn-mini" href="'.$rowRes['url'].'">Article URL: '.$rowRes['url'].'</a>';
		$result.='<hr/>';
		
	}
	
?>


<!DOCTYPE html >
<html>
<head>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="shortcut icon" href="assets/img/ico.png"/>
<title>search results</title>

</head>
<body>
<div class="container">
<div  style="text-align:center;padding:10px 0px 10px 0px">
<img src="assets/img/logo.jpg" alt="" style="width:10%"/>
</div>
<div class="span11"  >
<form class="form-search"  method="post"  action="results.php">
              
            <div class="input-append" style="width:100%;">
              <input type="text" class="span9 search-query" name="query" value="<?php print $query; ?>">
              <br class="visible-phone"/>
              <input type="submit" class=" span2 btn btn-inverse" style="float:none;min-height:30px" value="Search!" name="search" />
            </div>
           
          </form>
</div>
</div>
<hr/>
<div class="container">
<?php print $result;?>
</div>
</body>
</html>

