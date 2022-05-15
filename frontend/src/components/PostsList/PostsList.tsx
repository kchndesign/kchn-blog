import React, { useEffect, useState } from 'react';
import { Article, getAllArticles } from '../../services/api';

const PostsList: React.FC = () => {
    const [posts, setPosts] = useState<Article[]>([]);

    useEffect(() => {
        const init = async () => {
            setPosts(await getAllArticles());
        };

        init();
    }, []);

    return (
        <div>
            {posts.map((article: Article) => {
                return <p>{article.title}</p>;
            })}
        </div>
    );
};

export default PostsList;
