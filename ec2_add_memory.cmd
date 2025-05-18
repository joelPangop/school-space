#!/bin/bash

# 1. Vérifier le type de système de fichiers sur /
echo "Vérification du système de fichiers monté sur /..."
fstype=$(df -T / | tail -1 | awk '{print $2}')
echo "Type de système de fichiers détecté : $fstype"

# 2. Si XFS, exécuter xfs_growfs
# 3. Si EXT4, exécuter resize2fs
error=0
if [ "$fstype" = "xfs" ]; then
    echo "Extension du système de fichiers XFS en cours..."
    sudo xfs_growfs -d /    # étendre XFS sur la totalité du volume monté sur /
    if [ $? -ne 0 ]; then
        error=1
    fi
elif [ "$fstype" = "ext4" ]; then
    echo "Extension du système de fichiers EXT4 en cours..."
    sudo resize2fs /dev/xvda1   # étendre EXT4 sur le périphérique /dev/xvda1
    if [ $? -ne 0 ]; then
        error=1
    fi
else
    echo "Erreur : type de système de fichiers $fstype non pris en charge par ce script."
    error=1
fi

# 4. Afficher un message clair de succès ou d'erreur
if [ $error -eq 0 ]; then
    echo "Succès : le système de fichiers a été étendu (ou était déjà à la taille maximale)."
else
    echo "Échec : l'extension du système de fichiers n'a pas abouti."
fi

# 5. Afficher la sortie finale de df -h
echo "État de l'utilisation du disque après l'opération :"
df -h
