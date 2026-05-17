-- =========================================
-- users
-- =========================================

CREATE TABLE users (

    user_id VARCHAR(50) PRIMARY KEY,

    password_hash VARCHAR(255) NOT NULL,

    user_name VARCHAR(50) NOT NULL,

    profile_text VARCHAR(1000),

    mill_point INTEGER NOT NULL DEFAULT 0,

    secret_question VARCHAR(255) NOT NULL,

    secret_answer_hash VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

);

-- =========================================
-- posts
-- =========================================

CREATE TABLE posts (

    post_id SERIAL PRIMARY KEY,

    user_id VARCHAR(50) NOT NULL,

    content VARCHAR(200) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_posts_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE

);

-- =========================================
-- index
-- =========================================

CREATE INDEX idx_posts_user_id
ON posts(user_id);

CREATE INDEX idx_posts_created_at
ON posts(created_at DESC);