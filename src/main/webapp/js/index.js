/**
 * Javascript allows global variables/functions, which locate inside window.
 */
onload = function() {
	initAskButton();
};

var initAskButton = function(){
	var askButton = document.getElementById('askbtn');
	var qtext = document.getElementById('askinput');
	askButton.onclick = function(e) {
		getAnswer();
	};
	qtext.onkeyup = function(e) {
		if(e.keyCode == 13) // enter pressed
			getAnswer();
	}; 
};

var getAnswer = function() {
	// indicate loading
	var question = document.getElementById('askinput').value;
	answerEl = document.getElementById('answer');
	addClass(answerEl, 'content-hidden');
	var imgEl = document.getElementById('loading');
	removeClass(imgEl, 'content-hidden');
	
	if (question == '') {
		showAnswer('');
		return;
	}
	ajaxRequest('GET', encodeURI('definition/' + question), {
		'Accept': 'application/json'
	}, null, function(status, headers, body) { // success callback
		// show answer
		var ans = eval('(' + body + ')');
		showAnswer(ans.description);
	}, function(status, headers, body) {  // error callback
		switch (status) {
			case 404: 
				status404Handler();
			default:
				// do nothing
		}
	}, null); // run callbacks in global scope
};

var showAnswer = function(answer) {
	var answerEl = document.getElementById('answer');
	answerEl.innerHTML = escapeHtml(answer);
	removeClass(answerEl, 'content-hidden');
	// hide loading indicator
	var imgEl = document.getElementById('loading');
	addClass(imgEl, 'content-hidden');
};

var status404Handler = function() {
	showAnswer('Sorry, I don\'t understand your question!');
};

