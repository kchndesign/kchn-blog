import React, { useEffect, useState } from 'react';
import Skeleton from 'react-loading-skeleton';
import { Link } from 'react-router-dom';
import { Article, getAllArticles } from '../../services/api';
import { StyledCard } from '../../styled/Card/StyledCard';

const PostsList: React.FC = () => {
    // =================================
    // Fetching articles
    // =================================

    const [posts, setPosts] = useState<Article[]>([]);

    useEffect(() => {
        const init = async () => {
            setPosts(await getAllArticles());
        };

        init();
    }, []);

    return (
        <section aria-label="Recent Posts">
            <h2>Recent Posts</h2>
            {posts.map((article: Article) => {
                return (
                    <Link to={`article/${article.url}`}>
                        <StyledCard>
                            <p>{article.kicker || <Skeleton />}</p>
                            <h3>{article.title || <Skeleton />}</h3>
                            <p>
                                <em>{article.byline || <Skeleton />}</em>
                            </p>
                            <p>{article.metaDesc || <Skeleton />}</p>
                        </StyledCard>
                    </Link>
                );
            })}
        </section>
    );
};

export default PostsList;
