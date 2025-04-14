# 🎮 Wordle - Jeu en Java

Ce projet est une reproduction en ligne de commande du célèbre jeu **Wordle**, développé en **Java 21** avec **Maven**.

## ⚙️ Prérequis

- **Java 21**
- **Maven** installé (`mvn -v` pour vérifier)

## 🚀 Compilation et exécution

Dans le terminal, à la racine du projet :

```bash
mvn clean package
java -jar target/wordle-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## 🎯 Objectif du jeu

Devinez un mot mystère de **5 lettres** en **6 tentatives maximum**.

Après chaque proposition, vous recevez un retour pour chaque lettre :

- 🟢 Lettre correcte et bien placée  
- 🟡 Lettre correcte mais mal placée  
- ⚪ Lettre absente du mot


## 🧪 Tests

Pour exécuter uniquement les tests :

```bash
mvn test