[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/Fn6LoUUQ)
[![Docker Build & Push](https://github.com/softeng2223-inf-uniba/progetto2223-hellman/actions/workflows/docker_build&push.yml/badge.svg)](https://github.com/softeng2223-inf-uniba/progetto2223-hellman/actions/workflows/docker_build&push.yml)
[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-8d59dc4de5201274e310e4c54b9627a8934c3b88527886e3b421487c677d23eb.svg)](https://classroom.github.com/a/Fn6LoUUQ)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10672244&assignment_repo_type=AssignmentRepo)
# Battleship

La struttura della repository si presenta nel seguente modo:

```plaintext
|-- .github
|    |-- workflows
|    |      |-- docker_build&push.yml
|    |      |-- gradle_build.yml
|-- build
|    |-- reports
|    |      |-- checkstyle
|    |      |-- spotbugs
|    |      |-- tests/test
|–– config
|    |–– checkstyle
|    |–– pmd
|–– docs
|    |–– Assegnazione progetto.md
|    |–– Guida per lo studente.md
|    |–– ISPIRATORE.md
|    |–– img
|    |–– Report.md
|–– drawings
|–– gradle
|–– lib
|–– res
|–– src
|    |–– main
|    |–– test
|–– .gitignore
|–– build.gradle
|–– Dockerfile
|–– README.md
|–– gradlew
|–– gradlew.bat
|–– settings.gradle
```

Nel seguito si dettagliano i ruoli dei diversi componenti:

- `.github/workflows/docker_build&push.yml`: dettaglia le direttive per assicurare la *continuous integration* attraverso l’uso di GitHub Actions. In particolare, questo workflow si occupa di eseguire la build del progetto, creare un immagine Docker e pusharla su GitHub Packages;
- `.github/workflows/gradle_build.yml`: dettaglia le direttive per assicurare la *continuous integration* attraverso l’uso di GitHub Actions. In particolare, questo workflow si occupa di eseguire la build del progetto e di eseguire i test di unità;
- `build/`: ospita la sottocartella `reports/`, contenente gli output dei tool automatici di test e controllo di qualità;
- `config/`: ospita i file di configurazione. L’unica configurazione di base richiesta è quella per il tool checkstyle;
- `docs/`: ospita la documentazione di progetto, incluse le figure (nella sottocartella `img/`).
  Il file `Report.md` verrà usato per redigere la relazione finale del progetto.
  La cartella raccoglie inoltre:
  - `Assegnazione progetto.md`: contenente la descrizione dettagliata del progetto assegnato;
  - `Guida per lo studente.md`: contenente la descrizione di tutti i passi di configurazione necessari per l'attivazione del flusso di lavoro a supporto dello sviluppo del progetto;
  - `ISPIRATORE.md`: contenente una breve biografia e descrizione del personaggio che ha ispirato il progetto, in questo caso Martin Hellman;
- `gradle/`: ospita il `.jar` relativo al sistema di gestione delle dipendenze *Gradle*.
- `lib`: include eventuali librerie esterne utilizzate dal progetto.
- `res`: contiene risorse varie utilizzate dal sistema
- `src`: cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In `main/` ci saranno i file sorgente e `test/` conterrà i test di unità previsti.
- `drawings/`: contiene tutti i diagrammi UML usati per descrivere il progetto.
- `.gitignore`: specifica tutti i file che devono essere esclusi dal sistema di controllo versione.
- `build.gradle`: esplicita le direttive e la configurazione di *Gradle*.
- `gradlew` e `gradlew.bat`: eseguibili di *Gradle*, rispettivamente dedicati a Unix e Windows.
- `settings.gradle`: file di configurazione di *Gradle*.

In alcune cartelle è possibile notare la presenza di un unico file nascosto `.keep`: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.
