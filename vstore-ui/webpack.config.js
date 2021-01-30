const defaultsDeep = require('lodash.defaultsdeep');
var path = require('path');
var webpack = require('webpack');

// Plugins
var CopyWebpackPlugin = require('copy-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var UglifyJsPlugin = require('uglifyjs-webpack-plugin');

// PostCss
var autoprefixer = require('autoprefixer');
var postcssVars = require('postcss-simple-vars');
var postcssImport = require('postcss-import');

const STATIC_PATH = process.env.STATIC_PATH || '/static';

// 生产环境
// process.env.BABEL_ENV = 'production';
// process.env.NODE_ENV = 'production';

const base = {
    mode: process.env.NODE_ENV === 'production' ? 'production' : 'development',
    devtool: 'cheap-module-source-map',

    devServer: {
        contentBase: path.resolve(__dirname, 'build'),
        host: '0.0.0.0',
        port: process.env.PORT || 8601
    },
    output: {
        library: 'learn',
        filename: '[name].js',
        chunkFilename: 'chunks/[name].js'
    },
    externals: {
        React: 'react',
        ReactDOM: 'react-dom'
    },
    resolve: {
        symlinks: false,
        /**不加的话，必须 import 必须写全路径 **/
        extensions: [".js", ".json", ".jsx", ".css"]
    },
    module: {
        rules: [{
            test: /\.jsx?$/,
            loader: 'babel-loader',
            include: [
                path.resolve(__dirname, 'src')

            ],
            options: {
                // Explicitly disable babelrc so we don't catch various config
                // in much lower dependencies.
                babelrc: false,
                plugins: [
                    '@babel/plugin-syntax-dynamic-import',
                    '@babel/plugin-transform-async-to-generator',
                    '@babel/plugin-proposal-object-rest-spread'
                ],
                presets: ['@babel/preset-env', '@babel/preset-react']
            }
        },{
            test: /\.css$/,
            use: [
                {
                loader: 'style-loader'
            },

                {
                loader: 'css-loader',
                options: {
                    modules: true,
                    importLoaders: 1,
                    localIdentName: '[name]_[local]_[hash:base64:5]',
                    camelCase: true
                }
            }, {
                loader: 'postcss-loader',
                options: {
                    ident: 'postcss',
                    plugins: function () {
                        return [
                            postcssImport,
                            postcssVars,
                            autoprefixer
                        ];
                    }
                }
            }]
        },]
    },

    optimization: {
        minimizer: [
            new UglifyJsPlugin({
                include: /\.min\.js$/
            })
        ]
    },
    plugins: []
};

module.exports = [
    defaultsDeep({}, base, {
        // 生成入口，逐层加载依赖，生成js
        entry: {
            'lib.min': ['react', 'react-dom'],
            'comm': './src/components/comm.jsx',
            'index': './src/pages/index/index.jsx',
            'login': './src/pages/login/index.jsx'

        },
        output: {
            path: path.resolve(__dirname, 'build'),
            filename: '[name].js'
        },

        externals: {
            React: 'react',
            ReactDOM: 'react-dom'
        },
        module: {
            rules: base.module.rules.concat([
                {
                    test: /\.(svg|png|wav|gif|jpg)$/,
                    loader: 'file-loader',
                    options: {
                        outputPath: 'static/assets/'
                    }
                }
            ])
        },
        optimization: {
            splitChunks: {
                chunks: 'all',
                name: 'lib.min'
            },
            runtimeChunk: {
                name: 'lib.min'
            }
        },
        plugins: base.plugins.concat([
            new webpack.DefinePlugin({
                'process.env.NODE_ENV': '"' + process.env.NODE_ENV + '"',
                'process.env.DEBUG': Boolean(process.env.DEBUG),
                'process.env.GA_ID': '"' + (process.env.GA_ID || 'UA-000000-01') + '"'
            }),
            // 生成的js 引入到模板
            new HtmlWebpackPlugin({
                chunks: ['lib.min', 'comm','index'],
                template: 'src/pages/index/index.ejs',
                title: 'index',
                filename: 'index.html',
                inject:false
            }),
            // 生成的js 引入到模板
            new HtmlWebpackPlugin({
                chunks: ['lib.min', 'comm','login'],
                template: 'src/pages/login/index.ejs',
                title: 'login',
                filename: 'login.html',
                inject:false
            }),

            new CopyWebpackPlugin([{
                from: 'static',
                to: 'static'
            }])
        ])
    })
].concat(

);
