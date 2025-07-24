
/*Coletando os elementos que tem o id solicitado para adicionar evento de mouse-over*/
var isa = document.getElementById("link-isa")
var luiz = document.getElementById("link-luiz")

/*Adicionando o evento*/
isa.addEventListener("mouseover", mostrarI);
luiz.addEventListener("mouseover", mostrarL);

/*Eventos para esconder e mostrar cada um*/
function mostrarI(){
   document.getElementById("hover-insta-isa").style.display = "block";
}
function naoMostrarI(){
   document.getElementById("hover-insta-isa").style.display = "none";
}
function mostrarL(){
   document.getElementById("hover-insta-luiz").style.display = "block";
}
function naoMostrarL(){
   document.getElementById("hover-insta-luiz").style.display = "none";
}

