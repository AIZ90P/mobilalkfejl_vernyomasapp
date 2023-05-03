# Mobil alkalmazásfejlesztés projektmunka
Várnyomásnapló applikáció

## Mi ez a projekt?
Ez a projekt egy Vérnyomás monitorozó orvosi alkalmazás, amely lehetővé teszi a pácienseknek a vérnyomásmérő által jelzett értékeket (Szisztolés, valamint Diasztolés vérnyomás, valamint a Pulzus) feljegyezni dátumosan.
Jellemzően egy nap egyszer kell az embereknek naplóznia, de természetesen többször is mérheti a vérnyomását (csak ekkor már nem kell naplózni), ha úgy gondolja.

## Mit (nem) tartalmaz a projekt?
A projekt tartalmazza az értékelési táblázatban található szempontok nagy részét. Az alábbiakban igyekszem segíteni a javítási munkát, hogy ne kelljen sokáig keresni a megvalósításokat.

### Ami nincs benne
- Animáció
- Olyan androidos erőforrás használata, amely engedélyköteles
- Notification, alarm manager, job scheduler

### Ami benne van (és hol)
| Elem                                                      | Fájl                                                                                                                          | Sor                                                                                  |
|-----------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| Firebase auth, és regisztráció (4 pont)                   | MainActivity                                                                                                                  | Belépés: 34. sor; Regisztráció: 53.sor                                               |
| Adatmodell definiálása (2 pont)                           | MeresItem (class)                                                                                                             | Egész fájl.                                                                          |
| Legalább 3 különböző activity (2 pont)                    | MainActivity;MeresBevitel;MeresModositas;MeresTorles;VernyomasnaploLista                                                      | -                                                                                    |
| Beviteli mezők megfelelő típusúak (3 pont)                | A VernyomasnaploLista activity-n kívül minden más activity-ben.                                                               | -                                                                                    |
| ConstraintLayout és egy másik layout használata. (1 pont) | ActivityMain csak ConstraintLayout; list_item.xml CardView+LinearLayout+TableLayout; Bevitel, módosítás, törlés: TableLayout; | -                                                                                    |
| Reszponzivitás (3 pont)                                   | Minden activity tartalmaz landscape módot.                                                                                    | -                                                                                    |
| Intentek, minden elérhető activity-ként (2 pont)          | MainActivity;VernyomasnaploLista;MeresItemAdapter                                                                             | MainActivity: 71.sor; VernyomasnaploLista: 96. sor; MeresItemAdapter: 56. és 67. sor |
| LifecycleHook használata (2 pont)                         | VernyomasnaploLista (onResume)                                                                                                | 60. sor                                                                              |
| CRUD műveletek, de nincs külön szálon (5 pont)            | C:MeresBevitel; R:VernyomasnaploLista; U:MeresModositas; D:MeresTorles                                                        | C:44-45. sor; R:69. sor; U:78. sor; D:58. sor                                        |
| Legalább 2 Firestore lekérdezés (4 pont)                  | VernyomasnaploLista; MeresModositas                                                                                           | VernyomasnaploLista:69. sor; MeresModositas: 52. sor                                 |
| Szubjektív pontozás (max 5, min 0 pont)                   | -                                                                                                                             | -                                                                                    |
