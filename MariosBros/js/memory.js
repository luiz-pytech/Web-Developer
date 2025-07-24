/**Rederização no HTML*/

const cards =  document.getElementById("cardbord"); /**Coletando o div do id cardbord para renderizar as imagens no html*/

/**Guardando as imagens em um array para utilização */
const images = [
    'luigi.jpg',
    'mario.jpg',
    'princesaPeach.jpg',
    'toad.jpg',
    'waluigi.jpg',
    'yoshi.jpg'
];

/*Váriavel que vai receber as imagens, montar o conteudo*/
let cardHTML = '';

/*Percorrendo o array das imagens e montando o conteudo do div cardbord*/
images.forEach(img => {
    
    /**Passando a imagem  do indice do array como se fosse a frente que ficará para baixo e backcard que é as costas do card, como se fosse aqueles cards do UNO */
    cardHTML +=`
    <div class="memory-card" data-card="${img}" >
        <img class= "front" src="imgMemory/${img}"></img>
        <img class="back" src="imgMemory/frontcard.jpg"></img>
    </div>
    `
});
/*Passando o conteúdo montado para o cardbord lá do html*/
/*Obs: foi passado duas vezes para ter a carta e seu par*/
cards.innerHTML = cardHTML +cardHTML;


/*Montando um array com todos os .memory-card encontrados no html*/
const cardsMemory = document.querySelectorAll(".memory-card")

/*Adicionando um evento de click no card que irá auxiliar tanto para animações com css e para chamar as funções de verificações do jogo*/
cardsMemory.forEach(card=> {
    card.addEventListener('click', flipcards)
});
let primeiroCard; /**Váriavel equivalente ao primeiro card clicado */
let segundoCard; /**Váriavel equivalente ao segundo card clicado */
let cartasbloqueadas = false; /**Váriavel para bloquear as cartas depois de já terem a primeira e a segunda clicada */
let paresencontrados = 0;
/*função para adicionar a animação de virar a carta adicionando a classe flip*/
function flipcards(){
    /*Testando se as cartas estão bloqueadas, caso esteja um return false para sair do if*/
    if(cartasbloqueadas){
        return false
    }
    /*Adicionado a flip ao card que foi clicado*/
    this.classList.add('flip')
    /*Testando se já tem algo no primeiro card, se houver, sair do if, e adicionar o card clicado no segundo card*/
    if(!primeiroCard){
        primeiroCard = this;
        return false;
    }
    segundoCard = this;
    /*Chamando a função verificarCard, para testar se os cards são iguais*/
    verificarCard();
}
function verificarCard(){
    /*Váriavel boolean para ver os cards são iguais ou não*/
    let correto = false;
    
    /*Testando se as datas do card são iguais*/
    /*As datas do card são justamente o nome da imagem + extensão*/
    if(primeiroCard.dataset.card === segundoCard.dataset.card){
        correto = true; /*Caso seja, o correto passa a ser true*/
    }
    /*Caso não cair na condição de cima, o correto é falso e irá chamar a função retornarCard para virar os cards novamente*/
    if(correto == false){
        retornarCard();
    }
    /*Se correto for verdadeiro*/
    else if(correto){
        paresencontrados++;
        setTimeout(() => {
           if(paresencontrados == 6){
               alert("Parabéns você concluiu o jogo, vamos jogar novamente?")
           }
        }, 500)
        /*Bloquear as cartas*/
        cartasbloqueadas = true;
        /*Removendo os eventos de click do cards que foram virados*/
        primeiroCard.removeEventListener('click', flipcards);
        segundoCard.removeEventListener('click', flipcards);

        /*Resetando a jogada, para virar mais cards até finalizar o jogo*/
        primeiroCard = null;
        segundoCard = null;
        cartasbloqueadas = false;
    }
} 
/*Função aleatorio para sempre mudar a ordem das imagens já que o HTML posiciona na ordem que é o conteudo é colocado*/
(function aleatorio(){
    cardsMemory.forEach(cards =>{
        /*usando o mathfloor para arrendondar e o math.random para dá números aleatórios entre 0-1 e multiplicar por 12 que vai dá números parecidos com 11*/
        let posiAleatoria = Math.floor(Math.random() *12)
        /*Passando a posição aleatoria para o card do indice*/
        cards.style.order = posiAleatoria; 
    });
})();
function retornarCard(){
    cartasbloqueadas = true; /*Liberando para virar as cartas*/
    /*Um setTimeout para o tempo da função ser de 1s, se não nem aparecia o efeito de flip*/
    setTimeout(() => {
        /*Removendo o flip dos cards para eles voltarem a posição inicial, ou seja, para o backface*/
        primeiroCard.classList.remove('flip');
        segundoCard.classList.remove('flip');

        /*Resetando as jogadas*/
        primeiroCard = null;
        segundoCard = null;
        cartasbloqueadas = false;
    }, 1000)
}
