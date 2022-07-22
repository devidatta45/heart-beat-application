# ---  !Ups

create table if not exists heart_beat (
  user_id character varying not null primary key,
  movie_id character varying not null,
  duration bigint not null,
  position bigint not null,
  timestamp timestamp with time zone
);

# ---  !Downs

drop table if exists heart_beat;