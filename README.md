# Spring Boot 日記API

## 技術要素

| feature         | version | description            |
|-----------------|---------|------------------------|
| Java            | 21      | プログラミング言語              |
| Spring Boot     | 3.5.4   | フルスタックフレームワーク          |
| Spring Boot JPA | 3.5.4   | データベース操作のライブラリ/フレームワーク |
| MySQL           | 8.0.41  | リレーショナルデータベース          |
| Postman         | 11.47.0 | API開発ツール               |

## URL設計

| URL               | Method | Description      | Status Code    |
|-------------------|--------|------------------|----------------|
| /api/quiz/        | POST   | クイズ登録API（1件）     | 201 Created    |
| /api/quiz/{クイズID} | GET    | クイズ取得API（1件）     | 200 OK         |
| /api/quiz/        | GET    | クイズ取得API（複数件）    | 200 OK         |
| /api/quiz/{クイズID} | PUT    | クイズ更新API（1件）     | 200 OK         |
| /api/quiz/{クイズID} | DELETE | クイズ削除API（1件）     | 204 No Content |
| /api/quiz/random  | GET    | クイズ取得API（ランダム5件） | 200 OK         |

## DB設計

| type    | database name | table name |
|---------|---------------|------------|
| logical |               | クイズテーブル    |
| logical | quiz          | quiz       |

| logical | physical    | type            | UN | NN | PK | UQ | ZF | AI | default                                               |
|---------|-------------|-----------------|----|----|----|----|----|----|-------------------------------------------------------|
| ID      | id          | bigint unsigned | o  | o  | o  |    |    | o  |                                                       |
| カテゴリ    | category    | int             |    | o  |    |    |    |    |                                                       |
| 問題文     | question    | varchar(128)    |    | o  |    | o  |    |    |                                                       |
| 正解      | answer      | varchar(64)     |    | o  |    |    |    |    |                                                       |
| 解説      | explanation | varchar(512)    |    | o  |    |    |    |    |                                                       |
| 作成日時    | created_at  | datetime        |    | o  |    |    |    |    | default current_timestamp                             |
| 更新日時    | updated_at  | datetime        |    | o  |    |    |    |    | default current_timestamp on update current_timestamp |
