<?php

include_once("FullTextStemmer.php");
include_once("database.php");

class Similarity{

    //Atrributes...
	//query attrs
	private $query;
	private $stemmedQuery = array();
	private $arOfQueryIdf = array();
	private $arOfQueryTf = array();
	private $queryWeight = array();
	
	
	//doc attrs
	private $docWeight = array();
	private $stemmedDocs=array();
	
	
	
	//Methods...
	public function Similarity($query){
		
		$this->query = $query;
	}
	
	
	
	//------------------------query Methods Preperation------------------------
	//set query
	public function setQuery($query){
		$this->query = $query;
		
	}
	
	//stemmer
	public function doStemmingOnQuery(){
		
		$f = new FullTextStemmer();
		$f->setArticle($this->query);
		$f->setWordsCount(-1);
		$this->stemmedQuery = $f->Execute();
		
		//print_r($this->stemmedQuery);
		
	}	
	
	//calc TF for query
	public function calcTFQuery(){
		$maxOccur = max($this->stemmedQuery);
		foreach($this->stemmedQuery as $q=>$v){
			$this->arOfQueryTf[$q]= $v/$maxOccur;
		}
	
	}
	
	//cslc IDF for Query
	public function calcIDFQuery(){
		global $db;
		foreach($this->stemmedQuery as $q=>$v){
			$result = $db->query("select count(*) as occurences from min_words where word like '".$q."'");		
			$row = $db->fetch_array($result);
			$occur = $row["occurences"];
			$this->arOfQueryIdf[$q] = log10(101/($occur+1));
		}
			
	}
	
	//weight for query
	public function calcWeightForQuery(){
		
		foreach($this->arOfQueryIdf as $k=>$v){
			$this->queryWeight[$k] = $v*$this->arOfQueryTf[$k]; 	
		}
		
	}
	
	//inner product in general
	private function innerProd($ar1=array(),$ar2=array()){
		$temp = 0;
		for($i=0;$i<sizeof($ar1);$i++){
			$temp += $ar1[$i]*$ar2[$i];
		}
		return $temp;
	}
	//--------------------------------------------------Document Methods Preperation-------------------------------
	
	
	//calc weight for doc
	public function calcWeightForDoc(){
		global $db;	
		$result = $db->query("select * from min_words order by art_id asc");
		$i = 1;
		$temp = array();
		$docWeight=array();
		while( $row = $db->fetch_array($result) ){
			$idf = $row["idf"];
			$tf = $row["tf"];
			$idfMulTf = $idf*$tf; 
			$docWeight[$row["word"]]=$row["count"];
			$temp[$row["word"]] = $idfMulTf;
			$i++;
			if($i==50){
				$i = 1;
				$this->docWeight[$row ["art_id"]] = $temp;
				$this->stemmedDocs[$row ["art_id"]]=$docWeight;
			} else {
			//
			}
		}
	}
	
	
	//MIN Array Preperation
	public function min_prepare($docAr,$Wij){
		$qWieght=array();
		$docWieght=array();
		 foreach($this->stemmedQuery as $qKey=>$qValue){
			 foreach($docAr as $docKey=>$docValue){
				 if(strcmp($qKey,$docKey)==0){
					 $qWieght[]= $this->queryWeight[$qKey];
					 $docWieght[] = $Wij[$docKey];
					 break;
				}
			}
		}
	return(array($qWieght,$docWieght));
	}
	
	
	//---------------------------search-------------------- 
	
	public function search(){
	$allsimilarites=array();
	$this->doStemmingOnQuery();
	$this->calcTFQuery();
	$this->calcIDFQuery();
	$this->calcWeightForQuery();
	
	$this->calcWeightForDoc();
	$temp=0;
	
	foreach($this->docWeight as $k=>$ar){
		
		$arrOfElems=$this->min_prepare($this->stemmedDocs[$k],$ar);
		$p=$this->innerProd($arrOfElems[0],$arrOfElems[1]);
		//print $this->doCalculation($ar);
		$m=$this->doCalculation($ar)*$this->doCalculation($this->queryWeight);
		$allsimilarites[$k]	=$p/$m;
		 
		}
	
		 arsort($allsimilarites);
		return $allsimilarites;
	}
	
	private function doCalculation($ar=array()){
		$temp=0;
		foreach($ar as $key=>$value){
			
			//print $key."   ".$value."<br/>";
			$temp+=pow($value,2);
			
			
			}
			
		return sqrt($temp);
		}
	
	
	
}


//test test L L L


$sim= new Similarity("");




?>