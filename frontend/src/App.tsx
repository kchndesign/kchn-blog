import React from 'react';
import { Route, Router, Routes } from 'react-router-dom';

import './App.css';
import ArticlePage from './routes/Article/ArticlePage';
import Home from './routes/Home';
import NewArticle from './routes/NewArticle';

const App: React.FC = () => {
    return (
        <>
            <div className="App">
                <Routes>
                    {/* Home page */}
                    <Route path="/" element={<Home />} />
                    {/* New Post page */}
                    <Route path="/article/new" element={<NewArticle />} />
                    {/* Article page */}
                    <Route path="/article/:url" element={<ArticlePage />} />
                </Routes>
            </div>
        </>
    );
};

export default App;
