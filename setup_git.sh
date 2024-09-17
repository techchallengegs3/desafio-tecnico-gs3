#!/bin/bash

# Configurações de variáveis
GIT_REPO_URL="git@github.com:life-learning-42/fast-food-fiap-tech-challenge-soat6.git"
SSH_KEY_PATH="$HOME/.ssh/id_ed25519_life_learning_42"
EMAIL="eduardoalmmeida15@gmail.com"

# Função para verificar e gerar chave SSH, se necessário
setup_ssh() {
    if [ ! -f "$SSH_KEY_PATH" ]; then
        echo "Gerando nova chave SSH..."
        ssh-keygen -t ed25519 -C "$EMAIL" -f "$SSH_KEY_PATH" -N ""
    else
        echo "Chave SSH já existe: $SSH_KEY_PATH"
    fi

    echo "Corrigindo permissões da chave SSH..."
    chmod 600 "$SSH_KEY_PATH"
    chmod 644 "${SSH_KEY_PATH}.pub"

    echo "Adicionando chave SSH ao agente..."
    eval "$(ssh-agent -s)"
    ssh-add "$SSH_KEY_PATH"

    echo "Adicione a seguinte chave SSH ao GitHub:"
    cat "${SSH_KEY_PATH}.pub"
    echo "Acesse https://github.com/settings/keys e adicione a chave SSH."
}

# Função para configurar URL remota do Git
setup_git_remote() {
    echo "Configurando URL remota do Git..."
    git remote set-url origin "$GIT_REPO_URL"
}

# Função para testar a conexão SSH com o GitHub
test_ssh_connection() {
    echo "Testando conexão SSH com o GitHub..."
    ssh -T git@github.com
}

# Função para realizar push
git_push() {
    echo "Executando git push..."
    git push origin main
}

# Executar as funções
setup_ssh
setup_git_remote
test_ssh_connection
git_push

echo "Configuração concluída."
