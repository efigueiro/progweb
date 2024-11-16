// script.js

window.onload = function() {
	// Inicializando o Inputmask para o campo de data
	var im = new Inputmask('99/99/9999');  // Formato dd/mm/aaaa
	im.mask(document.getElementById('dataNascimento')); // Aplica a máscara ao campo dataNascimento
};
