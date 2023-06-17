# *Praktikum Pemograman Mobile*
-----
## MobCrafter

#### MobCrafter Development
- Mustofa Firdaus - 203040030 (Ketua)
- Dea Tirta Santika - 203040009
- Faisal Ibnul Hakim - 203040028
- Gilang Dwi Adira Karsoma - 203040014
- Ismail Fikri - 203040008
- Muhammad Rifky Rafidianto - 183040061

#### Tema Tugas Besar
Membuat aplikasi yang dapat mengelola 3 entitas, kemudian data bisa disimpan di server dan di device dengan menggunakan Scaffold dengan menu drawer dan bottom sheet.
Adapun tema yang dikerjakan adalah Perkuliahan yang memiliki entitas Dosen, Mahasiswa dan Matakuliah.

### Software
Software yang digunakan adalah Android Studio. Yang dimana Andorid Studio adalah Integrated Development Environment (IDE) resmi untuk pengembangan aplikasi Android dan menggunakan bahasa Pemrograman Kotlin.

#### Entitas
Entitas Dosen
Endpoint https://ppm-api.gusdya.net/api/dosen
| Kelas | Web API | Tipe |
|------| ------ | ------ |
|nidn| nidn | String |
|nama| nama | String |
|gelarDepan| gelar_depan | String |
|gelarBelakang| gelar_belakang | String |
|pendidikan| pendidikan | Enum (S2, S3) |

Entitas Mahasiswa
Endpoint https://ppm-api.gusdya.net/api/mahasiswa
| Kelas | Web API | Tipe |
|------| ------ | ------ |
|npm| npm | String |
|nama| nama | String |
|tanggalLahir| tanggal_lahir | Date |
|jenisKelamin| jenis_kelamin | Enum (Laki-laki, Perempuan) |

Entitas Matakuliah
Endpoint https://ppm-api.gusdya.net/api/matakuliah
| Kelas | Web API | Tipe |
|------| ------ | ------ |
|kode| kode | String |
|nama| nama | String |
|sks| sks | Tiny Integer |
|praktikum| praktikum | Boolean |
|deskripsi| deskripsi | Text |

#### Github
Akun github dari kelompok 2

| Nama | Akun Github |
| ------ | ------ |
| Mustofa | https://github.com/mustofa855 |
| Dean | https://github.com/Dean-Tr |
| Faisal | https://github.com/FaisalIbnulHakim |
| Gilang | https://github.com/Gilangdwi014 |
| Ismail | https://github.com/IsmailFikri12008 |
| Rifky |  |

![WhatsApp Image 2023-06-17 at 14 04 23](https://github.com/IsmailFikri12008/Praktikum-Mobile/assets/113658390/81cd4db2-525d-4bb7-ad9c-8ee944c64918)


#### Thanks To
- Mr. Ade Sukendar
- Bayu
- Fauzan
- Rizky
- Taufik Nur Sidik 

#### Licences
© 2023 Kelompok 2. All rights reserved.
