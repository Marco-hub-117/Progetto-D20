# Progetto D - Distributore automatico di bevande

## Istruzioni per poter utilizzare il programma

Per poter utilizzare correttamente il programma bisogna seguire i seguenti passi:

 - Scaricare uno dei due file presenti nella sezione Release di GitHub (l'unica differenza tra i due consiste nei cataloghi di bevande disponibili). **ATTENZIONE**: Se si vogliono testare solo le funzionalità offline del distributore (con Company e DB disattivi), clonare il branch master e avviare la classe it.unipv.ingsw.d20.vendingmachine.VendingMachineLauncher, non sono necessari i seguenti passaggi.
 
 - Accedere a Google Cloud Platform (https://cloud.google.com) con l'account Google abilitato: nell'angolo in alto a destra, di fianco all'icona del profilo Google, accedere a "Console";

 - Nella barra blu, appena a destra della scritta Google Cloud Platform, aprire la tendina dei progetti e selezionare "ingsw" (potrebbe non apparire subito, provare a cambiare "Seleziona da" o altre impostazioni nella finestra che si è aperta);
 
 - Aprire il menu di navigazione in alto a sinistra della barra blu e selezionare "SQL". Aprire l'istanza "ingsw20" e avviarla (richiede diversi minuti);
 
 - Dopo aver aperto nell' IDE il file precedentemente scaricato, procedere facendo partire la classe it.unipv.ingsw.d20.company.CompanyLauncher. Questo attiverà il server della compagnia e la web app;
 
 - Infine fare partire la classe it.unipv.ingsw.d20.vendingmachine.VendingMachineLauncher che permetterà di utilizzare il distributore vero e proprio.
 
 - Per la parte di web app, accedere da un browser a http://localhost:8080/d20/. Credenziali valide per l'accesso sono:
 
 username: adm
 
 password: adm
