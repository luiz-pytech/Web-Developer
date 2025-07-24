function validar(){
    var emailLogin = document.getElementById('email-login').value;
    var senhaLogin = document.querySelector('#password').value

    console.log(senhaLogin)
    if(emailLogin == "" || emailLogin == null){
        document.getElementById('email-login').classList.add('is-invalid')
        document.getElementById('invalid-email').style.display = 'flex'
        document.getElementById('email-login').focus()
        
    }else{
        document.getElementById('email-login').classList.remove('is-invalid')
        document.getElementById('email-login').classList.add('is-valid')
        document.getElementById('invalid-email').style.display = 'none'
    }

    if(senhaLogin  == "" || senhaLogin == null){
        
        document.getElementById('password').classList.add('is-invalid')
        document.getElementById('invalid-senha').style.display = 'flex'
        if(document.getElementById('invalid-email').style.display != 'flex'){
            document.getElementById('password').focus()
        }
    }else{
        document.getElementById('password').classList.remove('is-invalid')
        document.getElementById('password').classList.add('is-valid')
        document.getElementById('invalid-senha').style.display = 'none'

    }
}


function validarCadastro(){
    var nome = document.getElementById('nome').value;
    var email = document.getElementById('email-cadastro').value;
    var senha = document.getElementById('senha').value

    
    if(nome == "" || nome == null){
        document.getElementById('nome').classList.add('is-invalid')
        document.getElementById('invalid-nome').style.display = 'flex'
        document.getElementById('nome').focus()
        
    }else{
        document.getElementById('nome').classList.remove('is-invalid')
        document.getElementById('nome').classList.add('is-valid')
        document.getElementById('invalid-nome').style.display = 'none'
    }
    if(email  == "" || email == null){
        
        document.getElementById('email-cadastro').classList.add('is-invalid')
        document.getElementById('invalid-emailCadastro').style.display = 'flex'
        if(document.getElementById('invalid-nome').style.display != 'flex'){
            document.getElementById('email-cadastro').focus()
        }
    }else{
        document.getElementById('email-cadastro').classList.remove('is-invalid')
        document.getElementById('email-cadastro').classList.add('is-valid')
        document.getElementById('invalid-emailCadastro').style.display = 'none'

    }

    if(senha  == "" || senha == null){
        
        document.getElementById('senha').classList.add('is-invalid')
        document.getElementById('invalid-senhaCadastro').style.display = 'flex'
        if(document.getElementById('invalid-emailCadastro').style.display != 'flex'){
            document.getElementById('senha').focus()
        }
    }else{
        document.getElementById('senha').classList.remove('is-invalid')
        document.getElementById('senha').classList.add('is-valid')
        document.getElementById('invalid-senhaCadastro').style.display = 'none'

    }
}