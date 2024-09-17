#!/bin/bash

# Configurações de Git
GIT_NAME="Eduardo Almeida"
GIT_EMAIL="techchallengegs3@gmail.com"
REPO_URL_SSH="git@github.com-techchallengegs3:techchallengegs3/desafio-tecnico-gs3.git"
BRANCH="develop"  # ou "master", dependendo do nome do branch principal

# Solicita a mensagem de commit ao usuário
read -p "Digite a mensagem de commit: " COMMIT_MESSAGE

# Configura o Git globalmente
echo "Configurando Git globalmente..."
git config --global user.name "$GIT_NAME"
git config --global user.email "$GIT_EMAIL"
git config --global core.editor "nano"  # Pode alterar para seu editor preferido

# Verifica se o repositório local já existe
if [ -d ".git" ]; then
    echo "O repositório Git já está inicializado."
else
    echo "Inicializando repositório Git..."
    git init
fi

# Adiciona ou atualiza o repositório remoto 'origin'
echo "Adicionando ou atualizando repositório remoto 'origin'..."
if git remote get-url origin > /dev/null 2>&1; then
    git remote set-url origin "$REPO_URL_SSH"
else
    git remote add origin "$REPO_URL_SSH"
fi

# Verifica o branch principal no remoto
echo "Verificando branches remotos..."
REMOTE_BRANCH=$(git ls-remote --heads origin | grep "$BRANCH" | awk '{print $2}' | sed 's/refs\/heads\///')

if [ -z "$REMOTE_BRANCH" ]; then
    echo "Branch remoto '$BRANCH' não encontrado. Criando e empurrando novo branch..."
    git checkout -b "$BRANCH"
    git push -u origin "$BRANCH"
else
    echo "Branch remoto '$BRANCH' encontrado. Configurando o branch local para acompanhar o branch remoto..."
    git checkout -b "$BRANCH" || git checkout "$BRANCH"
    git branch --set-upstream-to=origin/"$BRANCH" "$BRANCH"
fi

# Adiciona todos os arquivos ao estágio
echo "Adicionando todos os arquivos ao estágio..."
git add .

# Realiza o commit
echo "Realizando commit com a mensagem: \"$COMMIT_MESSAGE\"..."
git commit -m "$COMMIT_MESSAGE"

# Empurra as alterações para o repositório remoto 'origin'
echo "Empurrando alterações para 'origin'..."
git push -u origin "$BRANCH"

# Realiza pull para garantir que está atualizado com o remoto
echo "Puxando alterações de 'origin'..."
git pull origin "$BRANCH"

echo "Configuração concluída. Repositório Git está pronto para push e pull."
