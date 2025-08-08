create table if not exists quiz
(
  id bigint unsigned not null auto_increment comment 'ID',
  category int not null comment 'カテゴリ',
  question varchar(128) not null comment '問題文',
  answer varchar(64) not null comment '正解',
  explanation varchar(512) not null comment '解説',
  created_at datetime not null default current_timestamp comment '作成日時',
  updated_at datetime not null default current_timestamp on update current_timestamp comment '更新日時',
  primary key (id),
  unique key (question)
) engine = innodb
  charset utf8mb4
  collate utf8mb4_bin comment 'クイズテーブル';