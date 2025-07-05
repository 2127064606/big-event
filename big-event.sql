create table user(
    id int unsigned primary key auto_increment comment '编号',
    username varchar(20) not null comment '用户名',
    password varchar(32) comment '密码',
    nickname varchar(10) comment '昵称',
    email varchar(128) comment '邮箱',
    user_pic varchar(128) comment '用户头像',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
)comment '用户表';

create table category(
    id int unsigned primary key auto_increment comment '编号',
    category_name varchar(32) not null comment '分类名称',
    category_alias varchar(32) not null comment '分类别名',
    create_user int unsigned comment '创建用户',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    constraint fk_category_user foreign key (create_user) references user(id)
);

create table article(
    id int unsigned primary key auto_increment comment '编号',
    title varchar(128) not null comment '标题',
    content varchar(10000) comment '内容',
    cover_img varchar(128) not null comment '封面图片',
    state varchar(3) default '草稿' comment '状态:[草稿] or [已发布]',
    category_id int unsigned comment '分类id',
    create_user int unsigned comment '创建用户',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    constraint fk_article_user foreign key (create_user) references user(id),
    constraint fk_article_category foreign key (category_id) references category(id)
);