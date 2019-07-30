<?php include_once("porter.php");?>
<?php
class FullTextStemmer{

	private $stopWords="/^(.|a|s|able|about|above|according|accordingly|across|actually|after|afterwards|again|against|ain|t|all|allow|allows|almost|alone|along|already|also|although|always|am|among|amongst|an|and|another|any|anybody|anyhow|anyone|anything|anyway|anyways|anywhere|apart|appear|appreciate|appropriate|are|aren|around|as|aside|ask|asking|associated|at|available|away|awfully|be|became|because|become|becomes|becoming|been|before|beforehand|behind|being|believe|below|beside|besides|best|better|between|beyond|both|brief|but|by|c|mon|came|can|cannot|cant|cause|causes|certain|certainly|changes|clearly|co|com|come|comes|concerning|consequently|consider|considering|contain|containing|contains|corresponding|could|couldn|course|currently|definitely|described|despite|did|didn|different|do|does|doesn|doing|don|done|down|downwards|during|each|edu|eg|eight|either|else|elsewhere|enough|entirely|especially|et|etc|even|ever|every|everybody|everyone|everything|everywhere|ex|exactly|example|except|far|few|fifth|first|five|followed|following|follows|for|former|formerly|forth|four|from|further|furthermore|get|gets|getting|given|gives|go|goes|going|gone|got|gotten|greetings|had|hadn|happens|hardly|has|hasn|have|haven|having|he|hello|help|hence|her|here|hereafter|hereby|herein|hereupon|hers|herself|hi|him|himself|his|hither|hopefully|how|howbeit|however|i|d|ll|m|ve|ie|if|ignored|immediate|in|inasmuch|inc|indeed|indicate|indicated|indicates|inner|insofar|instead|into|inward|is|isn|it|its|itself|just|keep|keeps|kept|know|known|knows|last|lately|later|latter|latterly|least|less|lest|let|like|liked|likely|little|look|looking|looks|ltd|mainly|many|may|maybe|me|mean|meanwhile|merely|might|more|moreover|most|mostly|much|must|my|myself|name|namely|nd|near|nearly|necessary|need|needs|neither|never|nevertheless|new|next|nine|no|nobody|non|none|noone|nor|normally|not|nothing|novel|now|nowhere|obviously|of|off|often|oh|ok|okay|old|on|once|one|ones|only|onto|or|other|others|otherwise|ought|our|ours|ourselves|out|outside|over|overall|own|particular|particularly|per|perhaps|placed|please|plus|possible|presumably|probably|provides|que|quite|qv|rather|rd|re|really|reasonably|regarding|regardless|regards|relatively|respectively|right|said|same|saw|say|saying|says|second|secondly|see|seeing|seem|seemed|seeming|seems|seen|self|selves|sensible|sent|serious|seriously|seven|several|shall|she|should|shouldn|since|six|so|some|somebody|somehow|someone|something|sometime|sometimes|somewhat|somewhere|soon|sorry|specified|specify|specifying|still|sub|such|sup|sure|take|taken|tell|tends|th|than|thank|thanks|thanx|that|thats|the|their|theirs|them|themselves|then|thence|there|thereafter|thereby|therefore|therein|theres|thereupon|these|they|think|third|this|thorough|thoroughly|those|though|three|through|throughout|thru|thus|to|together|too|took|toward|towards|tried|tries|truly|try|trying|twice|two|un|under|unfortunately|unless|unlikely|until|unto|up|upon|us|use|used|useful|uses|using|usually|value|various|very|via|viz|vs|want|wants|was|wasn|way|we|welcome|well|went|were|weren|what|whatever|when|whence|whenever|where|whereafter|whereas|whereby|wherein|whereupon|wherever|whether|which|while|whither|who|whoever|whole|whom|whose|why|will|willing|wish|with|within|without|won|wonder|would|wouldn|yes|yet|you|your|yours|yourself|yourselves|zero|lot|alot|pm)$/";	
	
	private $article="";
	private $tokenArray=array();
	private $filteredTokens=array();
	private $topWordsCount;
	
	public function FullTextStemmer(){	
		}
	
/*	public function FullTextStemmer($article,$count=10){
		$this->article=$article;
		$this->topWordsCount=$count;
		}*/
	//-------------------------setters and getters
	public function setArticle($art){$this->article=$art;}
	public function setWordsCount($count){$this->topWordsCount=$count;}	
	//--------------remove html tags && urls
	private function prepare(){
		$this->article=preg_replace("/(http(s)?:\/\/)?([\w-]+\.)+[\w-]+(\/[\w- \.\/\\?\%\&\=]*)?+(\/)?/"," ",$this->article);
		$this->article= preg_replace("/<(\/)?(\w+)([^>]*)>/"," ",$this->article);

		//return $content;
		}
	//--------get the tokens
	private function tokenize(){
		$this->tokenArray = preg_split("/[^a-z]/", $this->article, -1, PREG_SPLIT_NO_EMPTY);
		}
	//-----------remove the stop words	
	private function removeStopWords(){
		$this->filteredTokens=array_filter($this->tokenArray, function($x){
			 return !preg_match($this->stopWords,$x);
			});
		}	
	//------------porter stemmer
	private function Stem(){
		$arr=array();
		foreach($this->filteredTokens as $val){
			$arr[]=PorterStemmer::Stem($val);
			}
		$this->filteredTokens =$arr;
		}
	//------------ analyze  text
	private function analyze(){
		$wordFrequencyArray = array_count_values($this->filteredTokens);
        arsort($wordFrequencyArray);
		if($this->topWordsCount==-1){
			$topWords = $wordFrequencyArray;	
		} else{
			$topWords = array_slice($wordFrequencyArray,0,$this->topWordsCount);
		}
		
		return $topWords;
	} 	
	//----------------wrap it all up
	public function Execute(){
		$this->prepare();
		$this->tokenize();
		$this->removeStopWords();
		$this->Stem();
		return $this->analyze();
	}	
		
}//------------end of class
	




	
?>