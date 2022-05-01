function makeTemplate(list, targetClass) {
	let templateHTML = null;
	let bindTemplate = null;
	let resultHTML = "";

	for (let i = 0; i < list.length; i++) {
		if (list[i].saveFileName == "") {
			templateHTML = document.getElementById("listItemNoImage").innerHTML;
		} else {
			templateHTML = document.getElementById("listItemImage").innerHTML;
		}
		bindTemplate = Handlebars.compile(templateHTML);
		resultHTML += bindTemplate(list[i]);
	}
	document.querySelector(targetClass).innerHTML += resultHTML;

}