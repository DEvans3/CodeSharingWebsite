function copyMessage(el) {
	const str = el.parentElement.parentElement.children[0].innerHTML;
	let textArea = document.createElement('textarea');
	textArea.value = str;
	document.body.appendChild(textArea);
	textArea.select();
	document.execCommand('copy');
	document.body.removeChild(textArea);
}