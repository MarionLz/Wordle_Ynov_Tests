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

## 🧪 Tests

Pour exécuter uniquement les tests :

```bash
mvn test
```

## 🎯 Objectif du jeu

Devinez un mot mystère de **5 lettres** en **6 tentatives maximum**.

Après chaque proposition, vous recevez un retour pour chaque lettre :

- 🟢 Lettre correcte et bien placée  
- 🟡 Lettre correcte mais mal placée  
- ⚪ Lettre absente du mot

## 🎯 Scoring

Le score final dans le jeu est calculé en combinant deux éléments :

### 🔤 Bonus par lettre

Chaque lettre de votre proposition reçoit un bonus en fonction de sa précision par rapport au mot à deviner :

- ✅ Bien placée (verte)		+3
- ⚠️ Mal placée (jaune)		+1
- ❌ Pas dans le mot (grise)	+0


### 🔢 Points de base selon le nombre d’essais

Un nombre de points de base est attribué selon la rapidité avec laquelle le mot a été trouvé :

- 🥇 Trouvé en **1 coup** : **100 points**
- 2 coups : 90 points
- 3 coups : 80 points
- 4 coups : 70 points
- 5 coups : 60 points
- 6 coups : 50 points
- ❌ Pas trouvé : **0 point**

👉 **Score final = Points de base + Bonus des lettres**
